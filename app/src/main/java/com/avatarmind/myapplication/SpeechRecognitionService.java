package com.avatarmind.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpeechRecognitionService extends Service {

    private static final int SAMPLE_RATE = 16000;
    private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(
            SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);

    private AudioRecord audioRecord;
    private boolean isRecording = false;
    private Thread recordingThread;

    @Override
    public void onCreate() {
        super.onCreate();
        startRecording();
    }

    private void startRecording() {
        audioRecord = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                BUFFER_SIZE);

        if (audioRecord.getState() != AudioRecord.STATE_INITIALIZED) {
            Log.e("SpeechService", "AudioRecord initialization failed");
            return;
        }

        isRecording = true;
        audioRecord.startRecording();

        recordingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                streamAudioToServer();
            }
        }, "Audio Recording Thread");
        recordingThread.start();
    }

    private void streamAudioToServer() {
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            URL url = new URL("http://your-server-ip:5000/upload_audio");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/octet-stream");

            OutputStream outputStream = connection.getOutputStream();

            while (isRecording) {
                int bytesRead = audioRecord.read(buffer, 0, buffer.length);
                if (bytesRead > 0) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            outputStream.close();

            //Read response from backend
            connection.getResponseCode(); // Ensure the request is sent
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String command = reader.readLine();
            sendCommandToApp(command);
            reader.close();

        } catch (Exception e) {
            Log.e("SpeechService", "Error streaming audio: " + e.getMessage());
        }

    }

    private void sendCommandToApp(String command) {
        Intent intent = new Intent("com.avatarmind.SPEECH_COMMAND");
        intent.putExtra("command", command);
        sendBroadcast(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRecording();
    }

    private void stopRecording() {
        if (audioRecord != null) {
            isRecording = false;
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
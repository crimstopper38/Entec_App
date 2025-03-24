package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.robot.motion.RobotMotion;
import android.robot.speech.SpeechManager;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends Activity {

    private SpeechManager speechManager;
    private String recognizedText = ""; // Variable to store recognized text
    private Button generalQuestionsButton;
    private Button stemProgramsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        generalQuestionsButton = (Button) findViewById(R.id.general_questions_button);
        stemProgramsButton = (Button) findViewById(R.id.STEM_programs_button);

        // Set up button listeners
        setupButtonListeners();

        // Initialize SpeechManager
        speechManager = new SpeechManager(this, new SpeechManager.OnConnectListener() {
            @Override
            public void onConnect(boolean status) {
                if (status) {
                    Log.d("MainActivity", "SpeechManager connected successfully.");
                    setupAsr();
                    deliverWelcomeMessage(); // Deliver the welcome message
                } else {
                    Log.e("MainActivity", "Failed to connect to SpeechManager.");
                }
            }
        });
    }

    private void setupButtonListeners() {
        // General Questions Button
        generalQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(general_questions.class);
            }
        });

        // STEM Programs Button
        stemProgramsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(StemProgram.class);
            }
        });
    }

    private void setupAsr() {
        // Enable ASR
        speechManager.setAsrEnable(true);

        // Set up AsrListener
        speechManager.setAsrListener(asrListener);
    }

    private void deliverWelcomeMessage() {
        String welcomeMessage = getString(R.string.main_message);
        if (speechManager != null) {
            speechManager.setTtsListener(ttsListener);
            speechManager.startSpeaking(welcomeMessage);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Start listening for speech input (if not already listening)
        if (speechManager != null && !speechManager.isListening()) {
            speechManager.startListening();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop listening for speech input
        if (speechManager != null) {
            speechManager.stopListening();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up SpeechManager
        if (speechManager != null) {
            speechManager.shutdown();
        }
    }

    private void handleRecognizedText(String text) {
        if (text.toLowerCase().contains("general question")) {
            // Open the general_questions activity
            startNewActivity(general_questions.class);
        } else if (text.toLowerCase().contains("stem program")) {
            // Open the StemProgram activity
            startNewActivity(StemProgram.class);
        } else {
            // Default response for unrecognized commands
            speechManager.startSpeaking("I didn't understand that. Can you repeat?");
        }
    }

    private void startNewActivity(Class<?> targetActivity) {
        Intent intent = new Intent(MainActivity.this, targetActivity);
        startActivity(intent);
    }

    // TtsListener for welcome message
    private SpeechManager.TtsListener ttsListener = new SpeechManager.TtsListener() {
        @Override
        public void onBegin(int requestId) {
            Log.d("MainActivity", "TTS started for requestId: " + requestId);
        }

        @Override
        public void onEnd(int requestId) {
            Log.d("MainActivity", "TTS ended for requestId: " + requestId);
            // Start listening after the welcome message is complete
            if (speechManager != null) {
                speechManager.startListening();
            }
        }

        @Override
        public void onError(int requestId) {
            Log.e("MainActivity", "TTS error for requestId: " + requestId);
        }
    };

    // AsrListener for speech recognition
    private SpeechManager.AsrListener asrListener = new SpeechManager.AsrListener() {
        @Override
        public void onBegin() {
            Log.d("MainActivity", "ASR started.");
        }

        @Override
        public void onEnd() {
            Log.d("MainActivity", "ASR ended.");
        }

        @Override
        public void onError(int error) {
            Log.e("MainActivity", "ASR error: " + error);
        }

        @Override
        public boolean onResult(String text) {
            Log.d("MainActivity", "Recognized text: " + text);
            handleRecognizedText(text); // Process the recognized text
            return true; // Stop dispatching results to other listeners
        }

        @Override
        public void onVolumeChanged(float volume) {
            // Optional: Handle volume changes (e.g., update UI)
        }
    };
}
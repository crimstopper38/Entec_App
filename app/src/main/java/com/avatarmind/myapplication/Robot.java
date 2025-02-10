package com.avatarmind.myapplication;

import android.content.Context;
import android.robot.speech.SpeechManager;
import android.util.Log;

public class Robot {

    private static Robot instance;
    private SpeechManager mSpeechManager;

    public Robot(Context context) {
        try {
            mSpeechManager = (SpeechManager) context.getSystemService("speech");
            if (mSpeechManager == null) {
                Log.e("Robot", "SpeechManager service not available.");
            }
        } catch (Exception e) {
            Log.e("Robot", "Error initializing SpeechManager: " + e.getMessage());
        }
    }

    //Static method to get the single instance of the Robot
    public static Robot getInstance(Context context) {
        if (instance == null) {
            synchronized (Robot.class) { // Thread-safe initialization
                if (instance == null) {
                    instance = new Robot(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void speak(String message) {
        if (mSpeechManager != null) {
            try {
                mSpeechManager.forceStartSpeaking(message);
            } catch (Exception e) {
                Log.e("Robot", "Error during speech: " + e.getMessage());
            }
        } else {
            Log.e("Robot", "SpeechManager is not initialized. Cannot speak.");
        }
    }

    public void stopSpeaking() {
        if (mSpeechManager != null) {
            try {
                mSpeechManager.stopSpeaking(-1);
            } catch (Exception e) {
                Log.e("Robot", "Error stopping speech: " + e.getMessage());
            }
        } else {
            Log.e("Robot", "SpeechManager is not initialized. Cannot stop speaking.");
        }
    }
}

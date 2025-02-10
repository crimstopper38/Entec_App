package com.avatarmind.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SpeechCommandReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String command = intent.getStringExtra("command");

        if (command != null) {
            switch (command) {
                case "navigate:general_questions":
                    context.startActivity(new Intent(context, general_questions.class));
                    break;
                case "navigate:stem_program":
                    context.startActivity(new Intent(context, StemProgram.class));
                    break;
                case "navigate:advisement":
                    context.startActivity(new Intent(context, AdvisementInformationActivity.class));
                    break;
                default:
                    Log.e("SpeechReceiver", "Unknown command: " + command);
                    break;
            }
        }
    }
}
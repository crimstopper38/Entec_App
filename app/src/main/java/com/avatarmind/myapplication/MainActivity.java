package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.robot.motion.RobotMotion;
import android.util.Log;
import android.view.View;
import android.content.Intent;

public class MainActivity extends Activity {

    //initialize robot singleton and robot motion
    private Robot my_robot;
    private RobotMotion rMotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, SpeechRecognitionService.class);
        startService(serviceIntent);

        //gets shared robot instance
        my_robot = Robot.getInstance(this);

        //initialize robot motion
        rMotion = new RobotMotion();

        //intro robot speech
        String mainIntroText = getString(R.string.main_message);

        try {
            //initial robot speech
            if (my_robot != null) {
                my_robot.speak(mainIntroText);
            }

            //initial robot motion
            if (rMotion != null) {
                rMotion.doAction(RobotMotion.Action.WAVE);
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Error initializing Robot: " + e.getMessage());
        }

        //sets up buttons listeners
        setupButtonListeners();
    }

    //sets up all button listeners, new buttons should be added here
    private void setupButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.general_questions_button:
                        startNewActivity(general_questions.class);
                        break;

                    case R.id.STEM_programs_button:
                        startNewActivity(StemProgram.class);
                        break;

                    case R.id.exit_button:
                        finish();
                        break;

                    default:
                        break;
                }
            }
        };

        findViewById(R.id.general_questions_button).setOnClickListener(listener);
        findViewById(R.id.STEM_programs_button).setOnClickListener(listener);
        findViewById(R.id.exit_button).setOnClickListener(listener);
    }

    //starts the activity corresponding to the button pressed
    private void startNewActivity(Class<?> targetActivity) {
        Intent intent = new Intent(MainActivity.this, targetActivity);
        startActivity(intent);
    }

    //ends robot actions
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (my_robot != null) {
            my_robot.stopSpeaking();
        }
    }
}

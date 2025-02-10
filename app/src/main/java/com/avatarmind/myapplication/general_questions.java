package com.avatarmind.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.robot.motion.RobotMotion;
import android.util.Log;
import android.view.View;

public class general_questions extends Activity {

    private Robot my_robot;
    private RobotMotion rMotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_questions);

        //get shared robot instance
        my_robot = Robot.getInstance(this);

        rMotion = new RobotMotion();

        try {

            //speech performed by robot when view is opened
            if (my_robot != null) {
                //string that holds intro speech
                String introText = getString(R.string.general_questions_intro);
                my_robot.speak(introText);
            }

            //action performed by robot when view is opened
            if (rMotion != null) {
                rMotion.doAction(RobotMotion.Action.AKIMBO);
                rMotion.emoji(RobotMotion.Emoji.SHY);
            }

        } catch (Exception e) {
            Log.e("general_questions", "Error during initial actions: " + e.getMessage());
        }

        //setting up our listeners
        setupButtonListeners();
    }

    //sets up all button listeners, new buttons should be added here
    private void setupButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (my_robot != null) {
                    my_robot.stopSpeaking();
                }

                switch (v.getId()) {
                    case R.id.back_button:
                        finish();
                        break;

                    case R.id.advisement_info_button:
                        startNewActivity(AdvisementInformationActivity.class);
                        break;

                    case R.id.learning_options_button:
                        startNewActivity(LearningOptionsActivity.class);
                        break;

                    case R.id.scholarships_button:
                        startNewActivity(ScholarshipsActivity.class);
                        break;

                    case R.id.financial_aid_button:
                        startNewActivity(FinancialAid.class);
                        break;

                    default:
                        break;
                }
            }
        };

        //Assign listener to buttons
        findViewById(R.id.back_button).setOnClickListener(listener);
        findViewById(R.id.advisement_info_button).setOnClickListener(listener);
        findViewById(R.id.learning_options_button).setOnClickListener(listener);
        findViewById(R.id.scholarships_button).setOnClickListener(listener);
        findViewById(R.id.financial_aid_button).setOnClickListener(listener);
    }

    //
    private void startNewActivity(Class<?> targetActivity) {
        Intent intent = new Intent(general_questions.this, targetActivity);
        startActivity(intent);
    }

            @Override
    protected void onDestroy() {

        super.onDestroy();
        if (my_robot != null) {
            my_robot.stopSpeaking();
        }
    }
}

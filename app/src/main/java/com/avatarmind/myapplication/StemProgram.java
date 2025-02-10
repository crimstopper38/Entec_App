package com.avatarmind.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.robot.motion.RobotMotion;
import android.view.View;
import android.widget.Button;

public class StemProgram extends Activity {

    //initialize robot singleton and robot motion
    private Robot my_robot;
    private RobotMotion rMotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.stem_programs);

        //get shared robot instance
        my_robot = Robot.getInstance(this);

        //initiate robot motion
        rMotion = new RobotMotion();

        String stemProgramIntro = getString(R.string.stem_message);
        rMotion.doAction(RobotMotion.Action.AKIMBO);
        rMotion.emoji(RobotMotion.Emoji.SMILE);
        my_robot.speak(stemProgramIntro);

        Button buttonBack = (Button) findViewById(R.id.button_back);
        Button buttonAI =(Button) findViewById(R.id.button_ai);
        Button buttonSoftwareEngineering = (Button) findViewById(R.id.button_software_engineering);
        Button buttonDataAnalytics = (Button) findViewById(R.id.button_data_analytics);
        Button buttonCybersecurity = (Button) findViewById(R.id.button_cybersecurity);
        Button buttonNetworking = (Button) findViewById(R.id.button_networking);
        Button buttonElectricalEngineering = (Button) findViewById(R.id.button_electrical_engineering);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle AI button click
                Intent intent = new Intent(StemProgram.this, ArtificialIntelligenceActivity.class);
                startActivity(intent);
                my_robot.stopSpeaking();
            }
        });

        buttonSoftwareEngineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Software Engineering button click
                Intent intent = new Intent(StemProgram.this, SoftwareEngineeringActivity.class);
                startActivity(intent);
                my_robot.stopSpeaking();
            }
        });

        buttonDataAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Data Analytics button click
                Intent intent = new Intent(StemProgram.this, DataAnalyticsActivity.class);
                startActivity(intent);
                my_robot.stopSpeaking();
            }
        });

        buttonCybersecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Cybersecurity button click
                Intent intent = new Intent(StemProgram.this, CybersecurityActivity.class);
                startActivity(intent);
                my_robot.stopSpeaking();
            }
        });

        buttonNetworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Networking button click
                Intent intent = new Intent(StemProgram.this, NetworkingActivity.class);
                startActivity(intent);
                my_robot.stopSpeaking();
            }
        });

        buttonElectricalEngineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Electrical Engineering button click
                Intent intent = new Intent(StemProgram.this, ElectricalEngineeringActivity.class);
                startActivity(intent);
                my_robot.stopSpeaking();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (my_robot != null) {
            my_robot.stopSpeaking();
        }
    }



}

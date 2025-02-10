package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinancialAid extends Activity {

    private Robot my_robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_aid);

        //get shared robot instance
        my_robot = Robot.getInstance(this);

        speakAboutProgram();

        //back button functionality
        Button backButton = (Button) findViewById(R.id.financial_aid_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {

            //stops any robot actions when pressed
            @Override
            public void onClick(View v) {
                // *** might be unnecessary because of onDestroy check, test with robot ***
                my_robot.stopSpeaking();
                finish();
            }
        });
    }

    //text-to-speech description for the current page
    private void speakAboutProgram() {
        if (my_robot != null) {
            String financialMessage = getString(R.string.financial_aid_message);
            my_robot.speak(financialMessage);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (my_robot != null) {
            my_robot.stopSpeaking();
        }
    }
}

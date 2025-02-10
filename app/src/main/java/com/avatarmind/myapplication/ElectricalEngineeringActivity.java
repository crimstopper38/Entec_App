package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ElectricalEngineeringActivity extends Activity {

    private Robot my_robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical_engineering);

        my_robot = new Robot(this);

        speakAboutProgram();

        Button backButton = (Button) findViewById(R.id.button_back_ecet);
        if (backButton != null) {
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    my_robot.stopSpeaking();
                    finish();
                }
            });
        }
    }

    private void speakAboutProgram() {
        if (my_robot != null) {
            String message = "Miami Dade College offers a Bachelor of Science in Electrical and Computer Engineering Technology. "
                    + "This program provides hands-on experience in project management, teamwork, and technical writing. "
                    + "Graduates are prepared for careers as Engineering Technologists, Field Engineers, and more. "
                    + "For more information, please visit w w w dot m d c dot e d u slash electronics engineering b s.";
            my_robot.speak(message);
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

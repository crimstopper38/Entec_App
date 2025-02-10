package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoftwareEngineeringActivity extends Activity {

    private Robot my_robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_engineering);

        my_robot = new Robot(this);

        speakAboutProgram();

        Button backButton = (Button) findViewById(R.id.button_back_software_engineering);
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
            String message = "Miami Dade College offers a Bachelor of Science in Information Systems Technology with a Software Engineering Concentration. "
                    + "This program provides students with skills in software design, data structures, and systems analysis, preparing them for careers as software developers and programmers. "
                    + "For more information, please visit w w w dot m d c dot e d u slash software engineering.";
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

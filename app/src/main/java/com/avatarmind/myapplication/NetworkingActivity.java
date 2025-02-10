package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NetworkingActivity extends Activity {

    private Robot my_robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);

        my_robot = new Robot(this);

        speakAboutProgram();

        Button backButton = (Button) findViewById(R.id.button_back_networking);
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
            String message = "Miami Dade College offers a Bachelor of Science in Information Systems Technology with a Networking Concentration. "
                    + "This program provides critical skills in network design, implementation, and management, preparing graduates for roles like Network Administrators and Systems Analysts. "
                    + "For more information, please visit w w w dot m d c dot e d u slash information systems networking.";
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

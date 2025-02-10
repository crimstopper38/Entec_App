package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CybersecurityActivity extends Activity {

    private Robot my_robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cybersecurity);

        my_robot = new Robot(this);

        speakAboutProgram();

        // Back button functionality
        Button backButton = (Button) findViewById(R.id.button_back_cybersecurity);
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

    // Method to make the robot speak about the program
    private void speakAboutProgram() {
        if (my_robot != null) {
            String message = "Miami Dade College offers a Bachelor of Science in Cybersecurity. "
                    + "The program provides hands-on training in ethical hacking, information security management, "
                    + "network defense, penetration testing, and computer forensics. "
                    + "Recognized by the National Security Agency as a Center of Academic Excellence in Cyber Defense, "
                    + "graduates are prepared for roles such as Security System Administrators and Information Security Analysts. "
                    + "For more information, please visit w w w dot m d c dot e d u slash cybersecurity bs.";
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

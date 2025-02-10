package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ArtificialIntelligenceActivity extends Activity {

    private Robot my_robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artificial_intelligence);
        my_robot = new Robot(this);
        speakAboutProgram();

        Button backButton = (Button) findViewById(R.id.button_back_ai);
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
            String message = "Miami Dade College offers a Bachelor of Science in Applied Artificial Intelligence. "
                    + "This program provides practical skills in machine learning, natural language processing, "
                    + "and computer vision. It emphasizes ethical and socially responsible practices. "
                    + "Graduates are prepared for roles such as AI Analysts and Machine Learning Specialists. "
                    + "For more information, please visit double u double u double u dot m d c dot e d u slash applied a i b s.";
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

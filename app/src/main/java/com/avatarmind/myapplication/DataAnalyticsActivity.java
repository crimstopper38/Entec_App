package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataAnalyticsActivity extends Activity {

    private Robot my_robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analytics);

        my_robot = new Robot(this);

        speakAboutProgram();

        Button backButton = (Button) findViewById(R.id.button_back_data_analytics);
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
            String message = "Miami Dade College offers a Bachelor of Science in Data Analytics. "
                    + "This program provides hands-on training in data manipulation and analysis across industries. "
                    + "Graduates are prepared for roles such as Data Analysts and Business Intelligence Professionals. "
                    + "For more information, please visit w w w dot m d c dot e d u slash data analytics.";
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

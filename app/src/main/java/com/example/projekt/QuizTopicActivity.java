package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuizTopicActivity extends AppCompatActivity {
    private String selectedTopic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        final LinearLayout javaEE = findViewById(R.id.javaEELayout);
        final LinearLayout android = findViewById(R.id.androidLayout);
        final LinearLayout spring = findViewById(R.id.springLayout);
        final LinearLayout javaFX = findViewById(R.id.javaFXLayout);
        final Button startBtn = findViewById(R.id.startBtn);
        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView selectedTopicName = findViewById(R.id.selectedTopicName);

        final String getSelectedTopic = getIntent().getStringExtra("selectedTopic");

        selectedTopicName.setText(getSelectedTopic);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(QuizTopicActivity.this,MainActivity.class));
                finish();
            }
        });
        javaEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "javaEE";
                javaEE.setBackgroundResource(R.drawable.round_back_white_str10);

                android.setBackgroundResource(R.drawable.round_back_white10);
                spring.setBackgroundResource(R.drawable.round_back_white10);
                javaFX.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "android";
                android.setBackgroundResource(R.drawable.round_back_white_str10);

                javaEE.setBackgroundResource(R.drawable.round_back_white10);
                spring.setBackgroundResource(R.drawable.round_back_white10);
                javaFX.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        spring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "spring";
                spring.setBackgroundResource(R.drawable.round_back_white_str10);

                javaEE.setBackgroundResource(R.drawable.round_back_white10);
                android.setBackgroundResource(R.drawable.round_back_white10);
                javaFX.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        javaFX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "javaFX";
                javaFX.setBackgroundResource(R.drawable.round_back_white_str10);

                javaEE.setBackgroundResource(R.drawable.round_back_white10);
                android.setBackgroundResource(R.drawable.round_back_white10);
                spring.setBackgroundResource(R.drawable.round_back_white10);
            }
        });


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedTopic.isEmpty()){
                    Toast.makeText(QuizTopicActivity.this, "Wybierz temat", Toast.LENGTH_SHORT).show();
                } else {
                          Intent intent = new Intent(QuizTopicActivity.this, QuizActivity2.class);
                          intent.putExtra("selectedTopic",selectedTopic);
                          startActivity(intent);
                          finish();
                    }
                }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuizTopicActivity.this,MainActivity.class));
        finish();
    }
}
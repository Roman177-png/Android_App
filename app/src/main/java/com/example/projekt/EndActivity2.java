package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EndActivity2 extends AppCompatActivity {

    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end2);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final AppCompatButton nextBtn = findViewById(R.id.nextBtn);

        result = findViewById(R.id.result);

        int correct = getIntent().getIntExtra("correct",0);
        int wrong = getIntent().getIntExtra("wrong",0);

        result.setText("Prawidlowe odpowiedzi: " + correct + "\nNieprawidlowe odpowiedzi:" + wrong);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(EndActivity2.this,QuizTopicActivity.class));
                finish();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(EndActivity2.this, QuizTopicActivity.class);
                    startActivity(intent);
                    finish();
            }
        });

    }
    @Override
    public void onBackPressed() {


        startActivity(new Intent(EndActivity2.this,QuizTopicActivity.class));
        finish();
    }
}
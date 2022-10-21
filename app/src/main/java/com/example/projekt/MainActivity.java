package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String selectedTopic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final LinearLayout wyklad = findViewById(R.id.wykladLayout);
        final LinearLayout quiz = findViewById(R.id.quizLayout);
        final LinearLayout laboratorium = findViewById(R.id.laboratoriumLayout);
        final LinearLayout web = findViewById(R.id.webLayout);
        final Button startBtn = findViewById(R.id.startBtn);

        wyklad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "wyklad";
                wyklad.setBackgroundResource(R.drawable.round_back_white_str10);

                quiz.setBackgroundResource(R.drawable.round_back_white10);
                laboratorium.setBackgroundResource(R.drawable.round_back_white10);
                web.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "quiz";
                quiz.setBackgroundResource(R.drawable.round_back_white_str10);

                wyklad.setBackgroundResource(R.drawable.round_back_white10);
                laboratorium.setBackgroundResource(R.drawable.round_back_white10);
                web.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        laboratorium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "laboratorium";
                laboratorium.setBackgroundResource(R.drawable.round_back_white_str10);

                quiz.setBackgroundResource(R.drawable.round_back_white10);
                wyklad.setBackgroundResource(R.drawable.round_back_white10);
                web.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "web";
                web.setBackgroundResource(R.drawable.round_back_white_str10);

                quiz.setBackgroundResource(R.drawable.round_back_white10);
                laboratorium.setBackgroundResource(R.drawable.round_back_white10);
                wyklad.setBackgroundResource(R.drawable.round_back_white10);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedTopic.isEmpty()){
                    Toast.makeText(MainActivity.this, "Wybierz kategorie", Toast.LENGTH_SHORT).show();
                } else {
                    switch (selectedTopic)
                    {
                        case "quiz":
                            Intent intent = new Intent(MainActivity.this, QuizTopicActivity.class);
                            intent.putExtra("selectedTopic",selectedTopic);
                            startActivity(intent);
                            finish();
                            break;
                        case "wyklad":
                            Intent intent2 = new Intent(MainActivity.this, wykladActivity.class);
                            intent2.putExtra("selectedTopic",selectedTopic);
                            startActivity(intent2);
                            finish();
                            break;
                        case "laboratorium":
                            Intent intent3 = new Intent(MainActivity.this, labActivity.class);
                            intent3.putExtra("selectedTopic",selectedTopic);
                            startActivity(intent3);
                            finish();
                            break;
                        case "web":
                            Intent intent4 = new Intent(MainActivity.this, webActivity.class);
                            intent4.putExtra("selectedTopic",selectedTopic);
                            startActivity(intent4);
                            finish();
                    }
                }
            }
        });
    }
}
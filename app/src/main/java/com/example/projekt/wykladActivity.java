package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class wykladActivity extends AppCompatActivity {

    private String selectedWyklad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyklad);
        final TextView selectedTopicName = findViewById(R.id.selectedWyklad);
        final String getSelectedTopic = getIntent().getStringExtra("selectedTopic");
        final ImageView backBtn = findViewById(R.id.backBtn);
        final Button wyklad1 = findViewById(R.id.wyklad1);
        final Button wyklad2 = findViewById(R.id.wyklad2);
        final Button wyklad3 = findViewById(R.id.wyklad3);
        final Button wyklad4 = findViewById(R.id.wyklad4);
        final Button startBtn = findViewById(R.id.startBtn);


        selectedTopicName.setText(getSelectedTopic);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(wykladActivity.this,MainActivity.class));
                finish();
            }
        });

        wyklad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedWyklad = "wyklad1";
                wyklad1.setBackgroundResource(R.drawable.round_back_gradient_str10);

                wyklad2.setBackgroundResource(R.drawable.round_back_gradient);
                wyklad3.setBackgroundResource(R.drawable.round_back_gradient);
                wyklad4.setBackgroundResource(R.drawable.round_back_gradient);
            }
        });
        wyklad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedWyklad = "wyklad2";
                wyklad2.setBackgroundResource(R.drawable.round_back_gradient_str10);

                wyklad1.setBackgroundResource(R.drawable.round_back_gradient);
                wyklad3.setBackgroundResource(R.drawable.round_back_gradient);
                wyklad4.setBackgroundResource(R.drawable.round_back_gradient);
            }
        });
        wyklad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedWyklad = "wyklad3";
                wyklad3.setBackgroundResource(R.drawable.round_back_gradient_str10);

                wyklad2.setBackgroundResource(R.drawable.round_back_gradient);
                wyklad1.setBackgroundResource(R.drawable.round_back_gradient);
                wyklad4.setBackgroundResource(R.drawable.round_back_gradient);
            }
        });
        wyklad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedWyklad = "wyklad4";
                wyklad4.setBackgroundResource(R.drawable.round_back_gradient_str10);

                wyklad2.setBackgroundResource(R.drawable.round_back_gradient);
                wyklad3.setBackgroundResource(R.drawable.round_back_gradient);
                wyklad1.setBackgroundResource(R.drawable.round_back_gradient);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedWyklad.isEmpty()){
                    Toast.makeText(wykladActivity.this, "Wybierz wyklad", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(wykladActivity.this, Viewer1Activity.class);
                    intent.putExtra("selectedWyklad",selectedWyklad);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(wykladActivity.this,MainActivity.class));
        finish();
    }
}
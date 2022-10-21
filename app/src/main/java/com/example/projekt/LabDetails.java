package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class LabDetails extends AppCompatActivity {
    InputStream inputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_details);
        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView selectedExample = findViewById(R.id.selectedLaboratorium);

        final String getSelectedExample = getIntent().getStringExtra("selectedExample");
        final String getSelectedLab = getIntent().getStringExtra("selectedLab");
        final Button btn = (Button) findViewById(R.id.btnReadTxtFile);
        final TextView txt = (TextView) findViewById(R.id.txtFile);

        selectedExample.setText(getSelectedExample);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LabDetails.this, labActivity.class));
                finish();
            }
        });
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = "";
            try {
                switch(getSelectedLab){
                    case "Laboratorium1":
                        switch (getSelectedExample){
                            case "Example 1":
                                 inputStream = getAssets().open("Lab1Ex1.txt");
                                break;
                            case "Example 2":
                                inputStream = getAssets().open("Lab1Ex2.txt");
                                break;
                            case "Example 3":
                                inputStream = getAssets().open("Lab1Ex3.txt");
                                break;
                        }
                    break;
                    case "Laboratorium2":
                    switch(getSelectedExample){
                        case "Example 1":
                               inputStream = getAssets().open("Lab2Ex1.txt");
                            break;
                        case "Example 2":
                            inputStream = getAssets().open("Lab2Ex2.txt");
                            break;
                        case "Example 3":
                            inputStream = getAssets().open("Lab2Ex3.txt");
                            break;
                        case "Example 4":
                            inputStream = getAssets().open("Lab2Ex4.txt");
                            break;
                    }
                    break;
                    case "Laboratorium3":
                        switch(getSelectedExample){
                            case "Example 1":
                                inputStream = getAssets().open("Lab3Ex1.txt");
                                break;
                            case "Example 2":
                                inputStream = getAssets().open("Lab3Ex2.txt");
                                break;
                            case "Example 3":
                                inputStream = getAssets().open("Lab3Ex3.txt");
                                break;
                            case "Example 4":
                                inputStream = getAssets().open("Lab3Ex4.txt");
                                break;
                            case "Example 5":
                                inputStream = getAssets().open("Lab3Ex5.txt");
                                break;
                        }
                        break;
                    case "Laboratorium4":
                        switch(getSelectedExample){
                            case "Example 1":
                                inputStream = getAssets().open("Lab4Ex1.txt");
                                break;
                            case "Example 2":
                                inputStream = getAssets().open("Lab4Ex2.txt");
                                break;
                            case "Example 3":
                                inputStream = getAssets().open("Lab4Ex3.txt");
                                break;
                            case "Example 4":
                                inputStream = getAssets().open("Lab4Ex4.txt");
                                break;
                        }
                        break;
                    case "Laboratorium5":
                        switch(getSelectedExample){
                            case "Example 1":
                                inputStream = getAssets().open("Lab5Ex1.txt");
                                break;
                            case "Example 2":
                                inputStream = getAssets().open("Lab5Ex2.txt");
                                break;
                            case "Example 3":
                                inputStream = getAssets().open("Lab5Ex3.txt");
                                break;
                            case "Example 4":
                                inputStream = getAssets().open("Lab5Ex4.txt");
                                break;
                        }
                        break;
                    case "Laboratorium6":
                        switch(getSelectedExample){
                            case "Example 1":
                                inputStream = getAssets().open("Lab6Ex1.txt");
                                break;
                            case "Example 2":
                                inputStream = getAssets().open("Lab6Ex2.txt");
                                break;
                            case "Example 3":
                                inputStream = getAssets().open("Lab6Ex3.txt");
                                break;
                            case "Example 4":
                                inputStream = getAssets().open("Lab6Ex4.txt");
                                break;
                        }
                        break;
                }
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                text = new String(buffer);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            txt.setText(text);
            Toast.makeText(getApplicationContext(), "Read successfully !", Toast.LENGTH_SHORT).show();
        }
    });
}
    @Override
    public void onBackPressed() {
        startActivity(new Intent(LabDetails.this, labActivity.class));
        finish();
    }
}
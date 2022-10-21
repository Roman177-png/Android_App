package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity2 extends AppCompatActivity {

    TextView question;
    TextView questionNumber;
    CheckBox answer1,answer2,answer3,answer4;
    String jsonStr;
    String getSelectedTopic;
    private AppCompatButton nextBtn;
    private Timer quizTimer;

    private int seconds = 30;
    private int totalTimeInMins = 1;
    public ArrayList<String> mResult;
    public List<String>  list2;
    List<QuestionItem2> questionItem;
    int currentQuestion = 0;
    int correct = 0 , wrong = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        question = findViewById(R.id.question);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        final TextView timer = findViewById(R.id.timer);
        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView selectedTopicName = findViewById(R.id.selectedTopicName);
        questionNumber = findViewById(R.id.questionNumber);


        nextBtn = findViewById(R.id.nextBtn);
        mResult = new ArrayList<>();
        list2 = new ArrayList<>();
        getSelectedTopic = getIntent().getStringExtra("selectedTopic");

        selectedTopicName.setText(getSelectedTopic);

        //get all the question
        loadAllQuestions();
        //shuffle the questions
        Collections.shuffle(questionItem);
        //load first question
        setQuestionScreen(currentQuestion);

        startTimer(timer);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity2.this,QuizTopicActivity.class));
                finish();
            }
        });
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1.isChecked())
                    mResult.add(questionItem.get(currentQuestion).getAnswer1());
                else
                    mResult.remove(questionItem.get(currentQuestion).getAnswer1());
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer2.isChecked())
                    mResult.add(questionItem.get(currentQuestion).getAnswer2());
                else
                    mResult.remove(questionItem.get(currentQuestion).getAnswer2());
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer3.isChecked())
                    mResult.add(questionItem.get(currentQuestion).getAnswer3());
                else
                    mResult.remove(questionItem.get(currentQuestion).getAnswer3());
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer4.isChecked())
                    mResult.add(questionItem.get(currentQuestion).getAnswer4());
                else
                    mResult.remove(questionItem.get(currentQuestion).getAnswer4());
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cor1 = questionItem.get(currentQuestion).getCorrect1();
                list2.add(cor1);
                String cor2 = questionItem.get(currentQuestion).getCorrect2();
                list2.add(cor2);
                String cor3 = questionItem.get(currentQuestion).getCorrect3();
                list2.add(cor3);
                String cor4 = questionItem.get(currentQuestion).getCorrect4();
                list2.add(cor4);

                while(mResult.size()!=list2.size()){
                    mResult.add("");
                }

                Collections.sort(mResult);
                Collections.sort(list2);

                if (mResult.toString().equals(list2.toString())){
                    correct++;
                    Toast.makeText(QuizActivity2.this,"Prawidlowo",Toast.LENGTH_SHORT).show();
                }else{
                    wrong++;
                    Toast.makeText(QuizActivity2.this, "Nieprawidlowo! Prawidlowa odpowiedz:" + questionItem.get(currentQuestion).getCorrect1() +","+questionItem.get(currentQuestion).getCorrect2() + ","+questionItem.get(currentQuestion).getCorrect3()+ ","+questionItem.get(currentQuestion).getCorrect4(), + Toast.LENGTH_SHORT).show();

                }
                mResult.clear();
                list2.clear();

                //load next question if any
                if(currentQuestion < questionItem.size()-1){
                    currentQuestion++;
                    setQuestionScreen(currentQuestion);

                //Remowe all selected
                    answer1.setChecked(false);
                    answer2.setChecked(false);
                    answer3.setChecked(false);
                    answer4.setChecked(false);
                }else{
                    //game over
                    quizTimer.purge();
                    quizTimer.cancel();
                    Intent intent = new Intent(getApplicationContext(),EndActivity2.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("wrong",wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if( seconds <= 0 && totalTimeInMins != 0 ){
                    totalTimeInMins--;
                    //totalTimeInMins = totalTimeInMins - 1;
                    seconds = 59;
                } else if(seconds <= 0 && totalTimeInMins <= 0){
                    quizTimer.purge();
                    quizTimer.cancel();
                    Intent intent2 = new Intent(getApplicationContext(),EndActivity2.class);
                    intent2.putExtra("correct",correct);
                    intent2.putExtra("wrong",wrong);
                    //intent2.putExtra("quizTimer",quizTimer.toString());
                    startActivity(intent2);
                    finish();
                }
                else {
                    seconds --;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length() == 1){
                            finalMinutes = "0"+finalMinutes;
                        }
                        else if(finalSeconds.length() == 1){
                            finalSeconds = "0"+finalSeconds;
                        }
                        timerTextView.setText(finalMinutes + ":" + finalSeconds);
                    }
                });
            }
        },1000,1000);
    }


    //set question to the screen
    private void setQuestionScreen(int number){
            questionNumber.setText(currentQuestion + 1 + "/" + questionItem.size());
            question.setText(questionItem.get(number).getQuestion());
            answer1.setText(questionItem.get(number).getAnswer1());
            answer2.setText(questionItem.get(number).getAnswer2());
            answer3.setText(questionItem.get(number).getAnswer3());
            answer4.setText(questionItem.get(number).getAnswer4());
    }


    //make list with all the questions
    private void loadAllQuestions(){
        questionItem = new ArrayList<>();
        //load all questions into json string
        switch (getSelectedTopic){
            case "javaEE":
                jsonStr = loadJSONFromAsset("javaEE.json");
                break;
            case "android":
                jsonStr = loadJSONFromAsset("android.json");
                break;
            case "spring":
                jsonStr = loadJSONFromAsset("spring.json");
                break;
            case "javaFX":
                jsonStr = loadJSONFromAsset("javaFX.json");
                break;
        }
       //load all data into the list
        try {
            JSONObject jsonObject= new JSONObject(jsonStr);
            JSONArray questions = jsonObject.getJSONArray("questions");
            for(int i = 0; i < questions.length();i++){
                JSONObject question = questions.getJSONObject(i);

                String questionString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String answer3String = question.getString("answer3");
                String answer4String = question.getString("answer4");
                String correct1 = question.getString("correct1");
                String correct2 = question.getString("correct2");
                String correct3 = question.getString("correct3");
                String correct4 = question.getString("correct4");

                    questionItem.add(new QuestionItem2(
                            questionString,
                            answer1String,
                            answer2String,
                            answer3String,
                            answer4String,
                            correct1,
                            correct2,
                            correct3,
                            correct4
                    ));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    //load the json file from assets folder
    private  String loadJSONFromAsset(String file){
        String json = "";
        try{
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte [size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        }catch (Exception e ){
            e.printStackTrace();
        }
        return json;
    }
    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity2.this,QuizTopicActivity.class));
        finish();
    }
}
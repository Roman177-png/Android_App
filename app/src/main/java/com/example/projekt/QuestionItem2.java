package com.example.projekt;

import java.util.ArrayList;
import java.util.List;

public class QuestionItem2 {
   private String  question, answer1,answer2,answer3,answer4,correct1,correct2,correct3,correct4;
    public QuestionItem2(
                         String question, String answer1, String answer2, String answer3, String answer4,String correct1,String correct2,
                         String correct3,String correct4
    ) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct1 = correct1;
        this.correct2 = correct2;
        this.correct3 = correct3;
        this.correct4 = correct4;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getCorrect1() {
       return correct1;
    }
    public String getCorrect2() {
         return correct2;
     }
    public String getCorrect3() {
        return correct3;
    }
    public String getCorrect4() {
        return correct4;
    }
}

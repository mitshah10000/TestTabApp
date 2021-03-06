package com.example.u_braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button buttonGo, buttonPlayAgain;
    TextView textViewQuestion, textViewLabel, textViewPoints, textViewSeconds;
    ArrayList<Integer> answersList = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewLabel = findViewById(R.id.tv_result);
        textViewPoints = findViewById(R.id.tv_score);
        textViewQuestion = findViewById(R.id.tv_question);
        textViewSeconds = findViewById(R.id.tv_seconds);

        buttonPlayAgain = findViewById(R.id.btn_playAgain);
        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                numberOfQuestions = 0;
                new CountDownTimer(3100, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        textViewSeconds.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                    }

                    @Override
                    public void onFinish() {

                        textViewLabel.setText("Your score : " + textViewPoints.getText());
                    }
                }.start();
            }
        });

        buttonGo = findViewById(R.id.btn_go);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonGo.setVisibility(View.INVISIBLE);
            }
        });
        generateQuestion();

        new CountDownTimer(3100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewSeconds.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                textViewLabel.setText("Your score : " + textViewPoints.getText());
            }
        }.start();
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            textViewLabel.setText("Correct!");
        }else {
            textViewLabel.setText("Wrong!");
        }
        numberOfQuestions++;
        textViewPoints.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        textViewQuestion.setText(Integer.toString(a) + " + " + Integer.toString(b));

        Button button0 = findViewById(R.id.btn_opt1);
        Button button1 = findViewById(R.id.btn_opt2);
        Button button2 = findViewById(R.id.btn_opt3);
        Button button3 = findViewById(R.id.btn_opt4);
        locationOfCorrectAnswer = rand.nextInt(4);
        answersList.clear();
        int incorrectAnswer;
        for (int i=0; i<4; i++){
            if (i == locationOfCorrectAnswer){
                answersList.add(a + b);
            }else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answersList.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answersList.get(0)));
        button1.setText(Integer.toString(answersList.get(1)));
        button2.setText(Integer.toString(answersList.get(2)));
        button3.setText(Integer.toString(answersList.get(3)));
    }

}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
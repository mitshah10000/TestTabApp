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
                textViewSeconds.setText("30s");
                textViewPoints.setText("0/0");
                textViewLabel.setText("");
                new CountDownTimer(3100, 1000){

                    @Override
                    public void onTick(long millisUntilFinished) {
                        textViewSeconds.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                    }

                    @Override
                    public void onFinish() {
                        buttonPlayAgain.setVisibility(View.INVISIBLE);
                        textViewLabel.setText("Your score : " + textViewPoints.getText());
                    }
                }.start();
            }
        });

        buttonGo = findViewById(R.id.btn_go);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonGo.s
package com.myappcompany.darshan.trainyourbrain;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button theGoButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int scoreCounts = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView qTextView;
    int numberOfQuestions = 0;
    TextView timerTextView;
    Button playAgainButton;
    GridLayout gridLayout;

    public void playAgain(View view)
    {
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        timerTextView.setVisibility(View.VISIBLE);
        qTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        scoreCounts = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        resultTextView.setText("");
        scoreTextView.setText(Integer.toString(scoreCounts) + "/" + Integer.toString(numberOfQuestions));
        generateQuestions();
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l)
            {
                timerTextView.setText(Integer.toString((int)l/1000)+"s");
            }

            @Override
            public void onFinish()
            {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.airhornsound);
                mediaPlayer.start();
                resultTextView.setText("Time is up:(");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();

    }
    public void hereWeGo(View view)
    {
        playAgain(findViewById(R.id.timerTextView));
        theGoButton.setVisibility(View.INVISIBLE);
    }
    public void chooseAnswer(View view)
    {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct(^___^)");
                scoreCounts++;
            } else
                {
                resultTextView.setText("Wrong:(");
            }
            numberOfQuestions++;
            scoreTextView.setText(Integer.toString(scoreCounts) + "/" + Integer.toString(numberOfQuestions));
            generateQuestions();

    }
    public void generateQuestions()
    {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        qTextView.setText(Integer.toString(a) +" + "+ Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        for(int i=0; i<4; i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswers = random.nextInt(41);
                while(wrongAnswers == (a+b))
                {
                    wrongAnswers = random.nextInt(41);
                }
                answers.add(wrongAnswers);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theGoButton = findViewById(R.id.theGoButton);
        qTextView = findViewById(R.id.qTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gridLayout = findViewById(R.id.gridLayout);

        theGoButton.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);
        qTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
    }
}

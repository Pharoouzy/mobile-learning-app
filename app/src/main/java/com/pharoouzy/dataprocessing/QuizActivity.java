package com.pharoouzy.dataprocessing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar; //
    private Button  option1,
                    option2,
                    option3,
                    option4,
                    nextBtn;
    private RadioButton  optio1,
            optio2,
            optio3,
            optio4;
    private RadioGroup radioGroup;
    private TextView questionText; // displays current question to answer;
    private Questions questions = new Questions();
    private  int totalQuestions = questions.getTotal(); // total questions +" Total Question: "+totalQuestions
    private String answer; // correct answer for question in questionsText
    int score = 0; // current total score
    int wrong = 0; // for wrong answers
    int skipped = 1; // for skipped questions
    private int questionNumber = 0; // current question number

    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.quizStatusBarColor));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.quizNavBarColor));
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close_24dp);
        toolbar.setBackgroundColor(getResources().getColor(R.color.quizBarColor));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                exitQuiz();
            }
        });

        r = new Random();
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        nextBtn = (Button) findViewById(R.id.nextBtn);

        questionText = (TextView) findViewById(R.id.question);
        //updateQuestion(r.nextInt(totalQuestions));
        updateQuestion();

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuestion();
            }
        });
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.option1:
                checkOption(option1);
                break;
            case R.id.option2:
                checkOption(option2);
                break;
            case R.id.option3:
                checkOption(option3);
                break;
            case R.id.option4:
                checkOption(option4);
                break;
        }
    }

    public void checkOption(Button btn){
        if(btn.getText() == answer){
            score++;
            btn.setBackgroundColor(getResources().getColor(R.color.correct_color));
        }
        else{
            btn.setBackgroundColor(getResources().getColor(R.color.wrong_color));
        }
        disableButtons();
        nextBtn.setVisibility(View.VISIBLE);
    }

    private void updateQuestion(){
        toolbar.setTitle("Question " + (questionNumber+1) + " of " + totalQuestions);
        enableButtons();
        nextBtn.setVisibility(View.INVISIBLE);
        option1.setBackgroundColor(getResources().getColor(R.color.option_btn_color));
        option2.setBackgroundColor(getResources().getColor(R.color.option_btn_color));
        option3.setBackgroundColor(getResources().getColor(R.color.option_btn_color));
        option4.setBackgroundColor(getResources().getColor(R.color.option_btn_color));
        if(questionNumber < totalQuestions) {
            questionText.setText(questions.getQuestion(questionNumber));
            option1.setText(questions.getOption1(questionNumber));
            option2.setText(questions.getOption2(questionNumber));
            option3.setText(questions.getOption3(questionNumber));
            option4.setText(questions.getOption4(questionNumber));

            answer = questions.getAnswer(questionNumber);

            questionNumber++;
        }
        else {
            wrong = totalQuestions - score;
            Toast.makeText(QuizActivity.this, "Last question", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, ScoreCardActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("wrong", wrong);
            intent.putExtra("total", totalQuestions);
            intent.putExtra("skipped", skipped);
            startActivity(intent);
        }
    }


    private void enableButtons(){
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }

    private void disableButtons(){
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
    }

    /*private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
        alertDialogBuilder.setMessage("Game Over! Your Score is " + score + " points")
                .setCancelable(false)
                .setPositiveButton("Retake Quiz", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }*/

    public  void exitQuiz(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
        alertDialogBuilder.setMessage("Are you sure you want to quit the ongoing Quiz?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), QuizPromptActivity.class));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return ;
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed(){
            exitQuiz();
    }
}

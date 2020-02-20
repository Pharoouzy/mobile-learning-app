package com.pharoouzy.dataprocessing;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewQuestionsActivity extends AppCompatActivity {
    Toolbar toolbar, secondToolbar;
    RelativeLayout rootLayout;
    private Bundle extras;
    private Button option1,
            option2,
            option3,
            option4,
            nextBtn;
    private TextView questionText; // displays current question to answer;
    private Questions questions = new Questions();
    private int questionNumber = 0, total = 0, score = 0; // current question number
    private String answer, comment; // correct answer for question in questionsText
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_questions);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.quizStatusBarColor));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.quizNavBarColor));
        }
        rootLayout = (RelativeLayout) findViewById(R.id.main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        secondToolbar = (Toolbar) findViewById(R.id.secondToolbar);
        extras = getIntent().getExtras();
        // recieve the score from last activity by intent
        total = extras.getInt("total", 0);
        score = extras.getInt("score", 0);
        comment = extras.getString("comment", "");
        secondToolbar.setTitle(comment + " " + score + " of " + total + " correct");
        secondToolbar.setSubtitle("Swipe to review your answers");
        toolbar.setNavigationIcon(R.drawable.ic_close_24dp);
        toolbar.setBackgroundColor(getResources().getColor(R.color.quizBarColor));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        questionText = (TextView) findViewById(R.id.question);
        updateQuestion();

        rootLayout.setOnTouchListener(new OnSwipeTouchListener(ReviewQuestionsActivity.this){
            public void onSwipeLeft(){
                if(questionNumber < total){
                    updateQuestion();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Last", Toast.LENGTH_SHORT).show();
                }

            }

            public void onSwipeRight(){
                if(questionNumber < total){
                    updateQuestion();
                }
                else{
                    Toast.makeText(getApplicationContext(), "First", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void updateQuestion(){
        toolbar.setTitle("Question " + (questionNumber+1) + " of " + total);
        option1.setBackgroundColor(getResources().getColor(R.color.option_btn_color));
        option2.setBackgroundColor(getResources().getColor(R.color.option_btn_color));
        option3.setBackgroundColor(getResources().getColor(R.color.option_btn_color));
        option4.setBackgroundColor(getResources().getColor(R.color.option_btn_color));
        if(questionNumber < total) {
            questionText.setText(questions.getQuestion(questionNumber));
            option1.setText(questions.getOption1(questionNumber));
            option2.setText(questions.getOption2(questionNumber));
            option3.setText(questions.getOption3(questionNumber));
            option4.setText(questions.getOption4(questionNumber));
            answer = questions.getAnswer(questionNumber);
            questionNumber++;
        }
        else {
            Toast.makeText(getApplicationContext(), "Last question", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkOption(Button btn){
        if(btn.getText() == answer){
            btn.setBackgroundColor(getResources().getColor(R.color.correct_color));
        }
        else{
            btn.setBackgroundColor(getResources().getColor(R.color.wrong_color));
        }
    }

    @Override
    public void onBackPressed(){

        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}

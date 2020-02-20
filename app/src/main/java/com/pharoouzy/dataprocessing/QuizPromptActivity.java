package com.pharoouzy.dataprocessing;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class QuizPromptActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button yesButton, noButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_prompt);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.navBarColor));
        }
        yesButton = (Button) findViewById(R.id.yes_btn);
        noButton = (Button) findViewById(R.id.no_btn);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Quiz Prompt");
        toolbar.setNavigationIcon(R.drawable.ic_close_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(QuizPromptActivity.this, MainActivity.class));
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(QuizPromptActivity.this, QuizActivity.class));
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizPromptActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}

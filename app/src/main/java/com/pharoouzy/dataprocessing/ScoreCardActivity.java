package com.pharoouzy.dataprocessing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ScoreCardActivity extends AppCompatActivity {
    private TextView scoreText, highscoreText, score2nd, wrong2nd, commentText;
    private Button reviewBtn, retMainBtn;
    private Bundle extras;
    int score = 0;
    int total = 0;
    int wrong = 0;
    double percent = 0;
    String comment = "Great job!";
    String labels[] = {"Score", "Wrong"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        extras = getIntent().getExtras();

        scoreText = (TextView) findViewById(R.id.score);
        commentText = (TextView) findViewById(R.id.comment);
        highscoreText = (TextView) findViewById(R.id.highscore);
        reviewBtn = (Button) findViewById(R.id.review);
        retMainBtn = (Button) findViewById(R.id.retMainBtn);

        // recieve the score from last activity by intent
        total = extras.getInt("total", 0);
        score = extras.getInt("score", 0);
        wrong = extras.getInt("wrong", 0);
        scoreText.setText(score + " of " + total + " correct");

        // use Shared preference to save the best score
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        int highscore = preferences.getInt("highscore", 0);
        if(highscore >= score){
            // update existing highscore
            highscoreText.setText("High Score: " + highscore);
        }
        else {
            // saving new highscore
            highscoreText.setText("New High Score: " + score);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highscore", score);
            editor.commit();

        }

        retMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(ScoreCardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(getApplicationContext(), ReviewQuestionsActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("total", total);
                intent.putExtra("comment", comment);
                startActivity(intent);
            }
        });
        setupPieChart();
    }

    public void setupPieChart(){
        int scores[] = {score, wrong};
        // Populating a list of pie entries
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0; i < scores.length; i++){
            pieEntries.add(new PieEntry(scores[i], labels[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Score Card");
        // changing the pieChart color
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);
        data.setValueTextColor(Color.BLACK);
        // Get the chart
        PieChart chart = (PieChart) findViewById(R.id.pieChart);
//        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setData(data);
        // make the chart to animate
        chart.animateY(1000);
        // TO reDraw the pie chart
        chart.invalidate();
    }

    @Override
    public void onBackPressed(){
        finish();
        Intent intent = new Intent(ScoreCardActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

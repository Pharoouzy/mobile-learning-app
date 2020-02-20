package com.pharoouzy.dataprocessing;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    private TextView tv;
    private ImageView iv;
    private TextView tv2;
    private ProgressBar pb;
    private static int SPLASH_TIMEOUT = 2500;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // Setting up the nav bar color to transparent
            //getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationBarColor));
            // Activating fullscreen
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }

        tv = (TextView) findViewById(R.id.tv);
        pb = (ProgressBar) findViewById(R.id.pb);
        tv2 = (TextView) findViewById(R.id.tv2);
        iv = (ImageView) findViewById(R.id.iv);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splashtransition);
        tv.setAnimation(anim);
        tv2.setAnimation(anim);
        pb.setAnimation(anim);
        iv.setAnimation(anim);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}

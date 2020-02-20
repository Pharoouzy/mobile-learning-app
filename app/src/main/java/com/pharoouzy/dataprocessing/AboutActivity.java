package com.pharoouzy.dataprocessing;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.navBarColor));
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("About Application");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

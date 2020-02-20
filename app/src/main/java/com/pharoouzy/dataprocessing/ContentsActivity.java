package com.pharoouzy.dataprocessing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class ContentsActivity extends AppCompatActivity {
    private static final String TAG = "ContentsActivity";
    private Context mContext;
    private CoordinatorLayout rootLayout;
    private LinearLayout linearLayout;
    private Bundle extras;
    private WebView webView;
    private ProgressBar progressBar;
    Toolbar toolbar;
    private String title;
    private SlidrInterface slidrInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.navBarColor));
        }
        //slidrInterface = Slidr.attach(this);
        mContext = ContentsActivity.this;
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        rootLayout = (CoordinatorLayout) findViewById(R.id.content);
        progressBar = (ProgressBar) findViewById(R.id.preloader);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.myFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NoteActivity.class));
            }
        });

        webView = (WebView) findViewById(R.id.my_webview);
        extras = getIntent().getExtras();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        title = extras.getString("titles");
        title = title.replace("_", " ");
        toolbar.setTitle(title);
        toolbar.inflateMenu(R.menu.content_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // display a toast whenever the menu item is clicked
                String title = (String) menuItem.getTitle();
                Toast.makeText(getApplicationContext(), title+" Selected!", Toast.LENGTH_SHORT).show();
                switch (menuItem.getItemId()){
                    case R.id.notes :
                        showNotes();
                        break;
                    case R.id.share :

                        break;
                }
                return false;
            }
        });

        if (!extras.equals(null)){
            String data = extras.getString("titles");
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    progressBar.setVisibility(View.VISIBLE);
                    setTitle("Loading...");
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    progressBar.setVisibility(View.GONE);
                    setTitle(view.getTitle());
                }
            });
            String url = "file:///android_asset/"+data+".html";
            webView.loadUrl(url);

            WebSettings webSettings = webView.getSettings();
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setJavaScriptEnabled(true);
        }

//        swiper();
    }

    private void swiper() {
        webView.setOnTouchListener(new OnSwipeTouchListener(ContentsActivity.this){
            public void onSwipeLeft(){
                if(title.equals("Introduction")){
                    nextActivity("Types_of_Data_Models");
                }
                else if(title.equals("Types of Data Models")){
                    nextActivity("Data_Modelling");
                }
                else if(title.equals("Data Modelling")){
                    nextActivity("Normal_Forms");
                }
                else if(title.equals("Normal Forms")){
                    nextActivity("Entry_Relational_Models");
                }
                else if(title.equals("Entry Relational Models")){
                    nextActivity("Relational_Model");
                }
                else if(title.equals("Relational Model")){
                    nextActivity("File_Organization");
                }
                else if(title.equals("File Organization")){
                    Toast.makeText(getApplicationContext(), "Last page", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                }

            }

            public void onSwipeRight(){
                if(title.equals("Introduction")){
                    Toast.makeText(getApplicationContext(), "First page", Toast.LENGTH_SHORT).show();
                }
                else if(title.equals("Types of Data Models")){
                    previousActivity("Introduction");
                }
                else if(title.equals("Data Modelling")){
                    previousActivity("Types_of_Data_Models");
                }
                else if(title.equals("Normal Forms")){
                    previousActivity("Data_Modelling");
                }
                else if(title.equals("Entry Relational Models")){
                    previousActivity("Normal_Forms");
                }
                else if(title.equals("Relational Model")){
                    previousActivity("Entry_Relational_Models");
                }
                else if(title.equals("File Organization")){
                    previousActivity("Relational_Model");
                }
                else{
                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void nextActivity(String title){
        finish();
        Intent intent = new Intent(getApplicationContext(), ContentsActivity.class);
        intent.putExtra("titles", title);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void previousActivity(String title){
        finish();
        Intent intent = new Intent(getApplicationContext(), ContentsActivity.class);
        intent.putExtra("titles", title);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void showNotes(){
        startActivity(new Intent(getApplicationContext(), NoteListActivity.class));
    }

}

package com.pharoouzy.dataprocessing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout dl;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbarMenu();
        setupNavigationDrawerMenu();

        // Check if we are running on Android 5.0 or Higher
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            toolbar.setElevation(10f);
        }
    }
    private void setupToolbarMenu(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Data Processing");
        // importing main_menu.xml
        toolbar.inflateMenu(R.menu.main_menu);

        // Menu Item Click Listener
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // display a toast whenever the menu item is clicked
                String title = (String) menuItem.getTitle();
//                Toast.makeText(MainActivity.this, title+" Selected!", Toast.LENGTH_SHORT).show();
                switch (menuItem.getItemId()){
                    case R.id.about :
                        showAbout();
                        break;
                    case R.id.share :
                        showShare();
                        break;
                    case R.id.exit :
                        exitApp();
                        break;
                }
                return false;
            }
        });
    }
    private void setupNavigationDrawerMenu(){
        NavigationView nv = (NavigationView) findViewById(R.id.navView);
        dl = (DrawerLayout) findViewById(R.id.drawerLayout);
        nv.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle ad = new ActionBarDrawerToggle(this,
                dl,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        dl.addDrawerListener(ad);
        ad.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        closeDrawer();

        switch (item.getItemId()){
            case  R.id.notes :
                startActivity(new Intent(getApplicationContext(), NoteListActivity.class));
                break;
            case  R.id.exit :
                exitApp();
                break;
            case  R.id.dev :
                showAboutDev();
                break;
            case  R.id.con :
                showContact();
                break;
            case  R.id.quiz :
                showQuizPrompt();
                break;
            case  R.id.home :
                //Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.share :
                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    public void showTopic1(View view){
        Intent intent = new Intent(MainActivity.this, Topic1Activity.class);
        startActivity(intent);
    }

    public  void showTopic2(View view){
        startActivity(new Intent(MainActivity.this, Topic2Activity.class));
    }

    public  void showTopic3(View view){
        startActivity(new Intent(MainActivity.this, Topic3Activity.class));
    }

    public  void showTopic4(View view){
        startActivity(new Intent(MainActivity.this, Topic4Activity.class));
    }

    public void dummyClick(View view){
        //Toast.makeText(MainActivity.this, "Menu Selected!", Toast.LENGTH_SHORT).show();
    }

        public  void showAbout(){
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }

        public  void showAboutDev(){
            startActivity(new Intent(MainActivity.this, AboutDeveloperActivity.class));
        }

        public  void showContact(){
            String subject = "Help on Data Processing Mobile Application";
            String text = "";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"yusufumarfarouq@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(intent);
        }


    public  void showShare(){
        String subject = "Data Processing mobile application";
        String text = "Check out what I learned with Data Processing Mobile Application";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

    private void showQuizPrompt(){
        startActivity(new Intent(MainActivity.this, QuizPromptActivity.class));
    }

    private void closeDrawer(){
        dl.closeDrawer(GravityCompat.START);
    }

    private void showDrawer(){
        dl.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed(){
        if (dl.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }
        else{
            exitApp();
        }
    }

    private void exitApp(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("Are you sure you want to exit Data Processing mobile app?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //finish();
                        System.exit(0);
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
}

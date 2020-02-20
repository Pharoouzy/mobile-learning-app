package com.pharoouzy.dataprocessing;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Topic4Activity extends AppCompatActivity {
    private static final String TAG = "Topic4Activity";
    Toolbar toolbar;
    private Context mContext;

    ArrayList<String> titleArrayList;

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic4);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.navBarColor));
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setTitle("Computer Maintenance");
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mContext = Topic4Activity.this;
        // initializing titleArrayList
        titleArrayList = new ArrayList<String>();
        titleArrayList.add(Constants.ETHICS_AND_ISSUES);
        titleArrayList.add(Constants.MAINTENANCE_OF_COMPUTER);

        // for single activity I can define all this in Constants.java
        mRecyclerView = (RecyclerView) findViewById(R.id.title_rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        TitleAdapter adapter = new TitleAdapter(mContext, titleArrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(Topic4Activity.this, ContentsActivity.class);
                intent.putExtra("titles", titleArrayList.get(position));
                startActivity(intent);
                //Toast.makeText(Topic4Activity.this, "Clicked "+titleArrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(adapter);
    }
}

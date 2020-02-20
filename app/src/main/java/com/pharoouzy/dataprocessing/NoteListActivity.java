package com.pharoouzy.dataprocessing;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> adapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.navBarColor));
        }

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setTitle("My Notes");
        toolbar.inflateMenu(R.menu.menu_note_list);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        final ListView listNotes = (ListView) findViewById(R.id.list_notes);

        final List<NoteInfo> notes = DataManager.getInstance().getNotes();
        adapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        listNotes.setAdapter(adapterNotes);

        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
//                NoteInfo note =(NoteInfo) listNotes.getItemAtPosition(position);
                intent.putExtra(NoteActivity.NOTE_POSITION, position);
                startActivity(intent);
            }
        });
    }

}

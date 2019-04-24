package com.work.androidarchitecturecomponent;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int ADD_NOTE_REQUEST = 1;

    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;

    FloatingActionButton fabtnAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRecyclerView();

        initfabtnAddNote();

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.setNotes(notes);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == ADD_NOTE_REQUEST){

                String title = data.getStringExtra("title");
                String description = data.getStringExtra("description");
                int priority = data.getIntExtra("priority", 0);

                Note note = new Note(title, description, priority);

                noteViewModel.insert(note);

                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new NoteAdapter();

        recyclerView.setAdapter(adapter);
    }

    private void initfabtnAddNote() {
        fabtnAddNote = findViewById(R.id.fabtnAddNote);

        fabtnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult( new Intent(MainActivity.this, AddNoteActivity.class), ADD_NOTE_REQUEST);

            }
        });
    }



}

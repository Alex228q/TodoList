package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    NotesAdapter notesAdapter;
    private FloatingActionButton buttonAddNotes;
    private RecyclerView recyclerViewNotes;
    private final Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        notesAdapter = new NotesAdapter();

        recyclerViewNotes.setAdapter(notesAdapter);
        //Установка вида отображения элементов vertical horizontal grid (в простых вариантах можно задать в макете)
        //recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

        buttonAddNotes.setOnClickListener(v -> {
            Intent intent = AddNoteActivity.newIntent(this);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void initViews() {
        buttonAddNotes = findViewById(R.id.buttonAddNotes);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
    }

    private void showNotes() {
        notesAdapter.setNotes(database.getNotes());
    }
}











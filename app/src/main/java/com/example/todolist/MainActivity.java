package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    NotesAdapter notesAdapter;
    private FloatingActionButton buttonAddNotes;
    private RecyclerView recyclerViewNotes;
    private NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        notesDatabase = NotesDatabase.getInstance(getApplication());
        notesAdapter = new NotesAdapter();

        recyclerViewNotes.setAdapter(notesAdapter);
        //Установка вида отображения элементов vertical horizontal grid (в простых вариантах можно задать в макете)
        //recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Note note = notesAdapter.getNotes().get(position);
                notesDatabase.notesDao().remove(note.getId());
                showNotes();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

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
        notesAdapter.setNotes(notesDatabase.notesDao().getNotes());
    }
}











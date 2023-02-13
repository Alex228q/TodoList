package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    NotesAdapter notesAdapter;
    private FloatingActionButton buttonAddNotes;
    private RecyclerView recyclerViewNotes;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mainViewModel = new MainViewModel(getApplication());

        notesAdapter = new NotesAdapter();
        //Установка вида отображения элементов vertical horizontal grid (в простых вариантах можно задать в макете)
        //recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(notesAdapter);

        //в LiveDate запрос будет автоматически делаться в фоновом потоке(не нужно создавать новый поток)
        mainViewModel.getNotes().observe(
                this, (notes) -> notesAdapter.setNotes(notes));

        //в активити устанавливаем слушатель клика у адаптера на элемент(для этого добавим слушатель в адаптер с помощью интерфейса)
        notesAdapter.setOnNoteClickListener((note, view) -> {
            if (note.getText().trim().length() > 0) {
                Snackbar.make(view, note.getText(), Snackbar.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Note note = notesAdapter.getNotes().get(position);
                        mainViewModel.remove(note);
                    }
                });

        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

        buttonAddNotes.setOnClickListener(v -> {
            Intent intent = AddNoteActivity.newIntent(this);
            startActivity(intent);
        });
    }

    private void initViews() {
        buttonAddNotes = findViewById(R.id.buttonAddNotes);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
    }
}











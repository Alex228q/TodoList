package com.example.todolist;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final NotesDatabase notesDatabase;


    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstance(application);
    }

    public LiveData<List<Note>> getNotes(){
      return notesDatabase.notesDao().getNotes();
    }

    public void remove(Note note) {
        Thread thread = new Thread(() -> notesDatabase.notesDao().remove(note.getId()));
        thread.start();
    }
}

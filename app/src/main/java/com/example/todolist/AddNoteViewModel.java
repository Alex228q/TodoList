package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AddNoteViewModel extends AndroidViewModel {
    private final NotesDatabase notesDatabase;
    private final MutableLiveData<Boolean> shouldBeClosed = new MutableLiveData<>();

    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstance(getApplication());
    }

    public void saveNote(Note note) {
        Thread thread = new Thread(() -> {
            notesDatabase.notesDao().add(note);
            shouldBeClosed.postValue(true);
        });
        thread.start();
    }

    public LiveData<Boolean> getShouldCloseScreen() {
        return shouldBeClosed;
    }
}

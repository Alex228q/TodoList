package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private Button buttonSaveNote;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private NotesDatabase notesDatabase;

    //все работы с view выполняються только на главном потоке(handler хранит ссылку на какой-то поток)
    //метод finish() также относиться к работе с view
    private final Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        notesDatabase = NotesDatabase.getInstance(getApplication());
        initViews();

        buttonSaveNote.setOnClickListener(v -> saveNote());
    }

    private void saveNote() {
        String textNote = editTextNote.getText().toString().trim();
        int priority = getPriority();
        Note note = new Note(textNote, priority);
        Thread thread = new Thread(() -> {
            notesDatabase.notesDao().add(note);
            handler.post(this::finish);
        });
        thread.start();

    }

    private int getPriority() {
        int priority;
        if (radioButtonLow.isChecked()) {
            priority = 0;
        } else if (radioButtonMedium.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;
    }

    private void initViews() {
        editTextNote = findViewById(R.id.editTextNote);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }
}
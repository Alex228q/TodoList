package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private Button buttonSaveNote;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;

    private AddNoteViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        viewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
        initViews();

        viewModel.getShouldCloseScreen().observe(this, isClosed -> {
            if (isClosed) finish();
        });
        buttonSaveNote.setOnClickListener(v -> saveNote());
    }

    private void saveNote() {
        String textNote = editTextNote.getText().toString().trim();
        int priority = getPriority();
        Note note = new Note(textNote, priority);
        viewModel.saveNote(note);
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
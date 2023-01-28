package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton buttonAddNotes;
    private LinearLayout linearLayoutNotes;
    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            Note note = new Note("Note " + i, random.nextInt(3), i);
            notes.add(note);
        }
        showNotes();
    }

    private void initViews() {
        buttonAddNotes = findViewById(R.id.buttonAddNotes);
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
    }

    private void showNotes() {
        for (Note note : notes) {
            //Из макета xml создаем View элемент
            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutNotes,
                    false);

            //Из view элемента получаем TextView
            TextView textViewNote = view.findViewById(R.id.textViewNote);

            int colorResId;
            switch (note.getPriority()) {
                case 0:
                    colorResId = android.R.color.holo_green_light;
                    break;
                case 1:
                    colorResId = android.R.color.holo_orange_light;
                    break;
                default:
                    colorResId = android.R.color.holo_red_light;
            }

            //Получение цвета из id
            int color = ContextCompat.getColor(this, colorResId);

            textViewNote.setText(note.getText());
            textViewNote.setBackgroundColor(color);
            linearLayoutNotes.addView(textViewNote);
        }
    }
}











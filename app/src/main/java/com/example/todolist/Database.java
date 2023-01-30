package com.example.todolist;

import java.util.ArrayList;

public class Database {
    private final ArrayList<Note> notes = new ArrayList<>();

    //Singleton простая реализация
    static Database instance = null;

    static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }



    public void add(Note note) {
        notes.add(note);
    }

    public void remove(int id) {
        notes.removeIf(note -> note.getId() == id);
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }
}

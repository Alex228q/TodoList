package com.example.todolist;

public class Note {
    private String text;
    private int priority;
    private int id;

    public Note(String text, int priority, int id) {
        this.text = text;
        this.priority = priority;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }
}

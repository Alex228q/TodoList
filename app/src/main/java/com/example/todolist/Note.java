package com.example.todolist;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//Создаем из класса табицу для базы данных при помощи аннотации @Entity
@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private int priority;



    public Note(String text, int priority, int id) {
        this.text = text;
        this.priority = priority;
        this.id = id;
    }

    //конструктор который используем мы нужно пометить ignore иначе
    //room не поймет какой конструктор использовать
    @Ignore
    public Note(String text, int priority) {
        this.text = text;
        this.priority = priority;

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

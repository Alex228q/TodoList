package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Создаем базу данных аннотацией @Database указываем таблицы для этой базы и версию
@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase instance = null;
    private static final String NAME_DB = "notes.db";

    //room сгенирирует реализацию базы данных
    public static NotesDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            application,
                            NotesDatabase.class,
                            NAME_DB
                    ).allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    //room сгенирирует реализацию этого интерфейса
    public abstract NotesDao notesDao();
}

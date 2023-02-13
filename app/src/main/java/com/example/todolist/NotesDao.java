package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Интерфейс предостовляющий доступ к базе данных
//DAO -> data access object(объект доступа к данным)
@Dao
public interface NotesDao {

    @Query("SELECT * FROM notes")
        //Необходимо возвращать LIST,  room сам определит тип коллекции
        //При возвращении обьекта LiveData мы сможем подписаться на его изменения и реагировать на них
    LiveData<List<Note>> getNotes();

    @Insert
    void add(Note note);

    @Query("DELETE FROM notes WHERE id = :id")
    void remove(int id);
}

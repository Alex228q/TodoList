package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

//Интерфейс предостовляющий доступ к базе данных
//DAO -> data access object(объект доступа к данным)
@Dao
public interface NotesDao {

    @Query("SELECT * FROM notes")
    //обьект single позволяет подписаться на себя,от Completable отличаеться тем что умеет возвращать данные
    Single<List<Note>> getNotes();

    @Insert
    Completable add(Note note);

    @Query("DELETE FROM notes WHERE id = :id")
    Completable remove(int id);
}

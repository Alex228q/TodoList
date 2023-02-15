package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddNoteViewModel extends AndroidViewModel {
    private final NotesDatabase notesDatabase;
    private final MutableLiveData<Boolean> shouldBeClosed = new MutableLiveData<>();

    //коллекция обьектов Disposable для одновременного управления всеми подписками (например для отмены)
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstance(getApplication());
    }

    public void saveNote(Note note) {
        //компонент Disposable нужен для управления жизненным циклом подписки .subscribe() (например для отмены)
        Disposable disposable = notesDatabase.notesDao().add(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> shouldBeClosed.setValue(true));
        compositeDisposable.add(disposable);
    }


    public LiveData<Boolean> getShouldCloseScreen() {
        return shouldBeClosed;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}

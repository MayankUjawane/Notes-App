package com.example.notesapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;
    private final LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }

    /* Created a wrapper insert() method that calls the Repository's insert() method.
       In this way, the implementation of insert() is encapsulated from the UI
     */
    public void insert(Note note){
        mRepository.insert(note);
    }

    public void delete(Note note){
        mRepository.delete(note);
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }

}

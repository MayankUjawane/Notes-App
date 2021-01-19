package com.example.notesapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    NoteRepository(Application application){
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getOrderedNotes();
    }

    LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }

    void insert(Note note){
        NoteRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.insert(note);
            }
        });
    }

    void delete(Note note){
        NoteRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.delete(note);
            }
        });
    }

    void deleteAll(){
        NoteRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.deleteAll();
            }
        });
    }

}

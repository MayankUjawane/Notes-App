package com.example.notesapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM "+ Constants.name)
    void deleteAll();

    @Query("SELECT * FROM " + Constants.name + " ORDER BY "+ Constants.columnName + " ASC")
    LiveData<List<Note>> getOrderedNotes();
}

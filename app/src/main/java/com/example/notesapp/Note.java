package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = Constants.name)
public class Note {
    public Note(@NonNull String mNotes){
        this.mNotes = mNotes;
    }

    @PrimaryKey (autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    @ColumnInfo (name = Constants.columnName)
    private String mNotes;


    public String getNotes() {
        return mNotes;
    }
}

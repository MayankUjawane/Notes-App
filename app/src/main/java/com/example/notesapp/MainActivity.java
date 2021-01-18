package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickAdapter {

    Application application;
    NoteViewModel viewModel;
    TextView inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NotesRVAdapter adapter = new NotesRVAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        viewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.updateData((ArrayList<Note>) notes);
            }
        });
    }

    @Override
    public void onItemClicked(Note note) {
        viewModel.delete(note);
        Toast.makeText(this, "" + note.getNotes() + " Deleted", Toast.LENGTH_LONG).show();
    }

    public void submitNote(View view){
        inputText = findViewById(R.id.tv_intput);
        String noteText = inputText.getText().toString();
        if(noteText != null){
            Note note = new Note(noteText);
            viewModel.insert(note);
            Toast.makeText(this, "" + note.getNotes() + " Inserted", Toast.LENGTH_LONG).show();
        }
    }
}
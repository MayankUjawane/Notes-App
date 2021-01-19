package com.example.notesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesRVAdapter extends RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder> {

    OnClickAdapter listener;
    public NotesRVAdapter(OnClickAdapter listener){
        this.listener = listener;
    }
    ArrayList<Note> allNotes = new ArrayList<>();

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(view);
        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(allNotes.get(viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = allNotes.get(position);
        holder.tv_items.setText(currentNote.getNotes());

    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void updateData(ArrayList<Note> note){
        allNotes.clear();
        allNotes.addAll(note);

        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tv_items;
        ImageView iv_delete;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_items = itemView.findViewById(R.id.tv_items);
            iv_delete = itemView.findViewById(R.id.iv_delete);
        }
    }
}

interface OnClickAdapter{
    void onItemClicked(Note note);
}
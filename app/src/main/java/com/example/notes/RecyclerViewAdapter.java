package com.example.notes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewViewHolder> {
    private ArrayList<String> notesList;
    private ArrayList<String> titleList;


    public RecyclerViewAdapter(ArrayList<String> notesList) {
        this.notesList = notesList;
    }

    public void updateData(ArrayList<String> newNotesList, ArrayList<String> newTitleList) {
        this.notesList = newNotesList;
        this.titleList = newTitleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_edittext, parent, false);
        return new RecyclerViewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        String[] noteParts = notesList.get(position).split("\n");

        if (noteParts.length >= 2) {
            holder.titleTextView.setText(noteParts[0]);
            holder.notesTextView.setText(noteParts[1]);
        } else {
            // If there's no title, display the entire note as the content
            holder.titleTextView.setText("");
            holder.notesTextView.setText(noteParts[0]);
        }

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}



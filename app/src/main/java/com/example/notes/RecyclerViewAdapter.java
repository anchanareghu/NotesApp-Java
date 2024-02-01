package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewViewHolder> {
    ArrayList<String> notesList;
    Context context;

    public RecyclerViewAdapter(ArrayList<String> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
    }

    public void updateData(ArrayList<String> newNotesList) {
        this.notesList = newNotesList;
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
            holder.titleTextView.setText("");
            holder.notesTextView.setText(noteParts[0]);
        }

        String title = noteParts[0];
        String note = noteParts[1];
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotesDetailActivity.class);
                intent.putExtra("note_title", title);
                intent.putExtra("note_content", note);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}





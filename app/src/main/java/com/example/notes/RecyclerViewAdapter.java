package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showConfirmationDialog( holder.getAdapterPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
    private void deleteNoteAt(int position) {
        notesList.remove(position);
        notifyItemRemoved(position);
        saveNotesToSharedPreferences(); // Update SharedPreferences after deletion
    }

    private void saveNotesToSharedPreferences() {
        SharedPreferences preferences = context.getSharedPreferences("MyNotesPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> notesSet = new HashSet<>(notesList);
        editor.putStringSet("notes", notesSet);
        editor.apply();
    }
    private void showConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this note?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteNoteAt(position);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

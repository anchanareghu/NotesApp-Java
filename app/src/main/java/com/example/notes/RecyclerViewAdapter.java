package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Note> notes = new ArrayList<>();
    private final Context context;
    private final NotesDatabase notesDatabase;
    private int colorIndex = 0;


    public RecyclerViewAdapter(Context context, NotesDatabase notesDatabase) {
        this.context = context;
        this.notesDatabase = notesDatabase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.contentTextView.setText(note.getContent());

        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, getRandomColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotesDetailActivity.class);
                intent.putExtra("note_id", note.getId());
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showConfirmationDialog(note.getId());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setData(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public int getRandomColor() {
        int[] colors = {R.color.peach, R.color.lemon, R.color.sage, R.color.orange};
        int color = colors[colorIndex];
        colorIndex = (colorIndex + 1) % colors.length;
        return color;
    }


    private void deleteNoteById(int noteId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesDatabase.noteDao().deleteById(noteId);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                try {
                    loadNotes();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
            }
        }.execute();
    }

    private void loadNotes() throws IllegalAccessException, InstantiationException {
        notesDatabase.noteDao().getAllNotes().observe(MainActivity.class.newInstance(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                setData(notes);
            }
        });
    }

    private void showConfirmationDialog(final int noteId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this note?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteNoteById(noteId);
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView titleTextView;
        TextView contentTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}

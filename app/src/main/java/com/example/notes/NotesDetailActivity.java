package com.example.notes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class NotesDetailActivity extends AppCompatActivity {
    private EditText editTitleView;
    private EditText editNoteView;
    private int noteId;
    private NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes);
        getSupportActionBar().hide();

        editTitleView = findViewById(R.id.title);
        editNoteView = findViewById(R.id.note);
        notesDatabase = NotesDatabase.getDatabase(this);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("note_id", -1);

        loadNote();

        View back_icon = findViewById(R.id.back);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View backButton) {
                onBackPressed();
            }
        });

    }

    private void loadNote() {
        notesDatabase.noteDao().getNoteById(noteId).observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                if (note != null) {
                    editTitleView.setText(note.getTitle());
                    editNoteView.setText(note.getContent());
                }
            }
        });
    }

    private void saveNote() {
        String title = editTitleView.getText().toString();
        String content = editNoteView.getText().toString();

        Note updatedNote = new Note();
        updatedNote.setId(noteId);
        updatedNote.setTitle(title);
        updatedNote.setContent(content);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesDatabase.noteDao().update(updatedNote);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                setResult(RESULT_OK);
                finish();
            }
        }.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNote();
    }
}

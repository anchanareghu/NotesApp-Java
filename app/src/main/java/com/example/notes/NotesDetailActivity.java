package com.example.notes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NotesDetailActivity extends AppCompatActivity {
    private EditText editTitleView;
    private EditText editNoteView;
    private int noteId;
    private NotesDatabase notesDatabase;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes);
        Objects.requireNonNull(getSupportActionBar()).hide();

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
                    currentNote = note;
                }
            }
        });
    }

    private void saveNoteIfChanged() {
        String note = editNoteView.getText().toString();
        String title = editTitleView.getText().toString();

        if (currentNote != null && (!currentNote.getTitle().equals(title) || !currentNote.getContent().equals(note))) {
            Note newNote = new Note();
            newNote.setId(noteId);
            newNote.setTitle(title);
            newNote.setContent(note);

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    notesDatabase.noteDao().update(newNote);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
                }
            });
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNoteIfChanged();
    }
}


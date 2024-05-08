package com.example.notes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewNoteActivity extends AppCompatActivity {
    private EditText noteEditText;
    private EditText titleEditText;
    private NotesDatabase notesDatabase;
    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes);

        Objects.requireNonNull(getSupportActionBar()).hide();

        View back_icon = findViewById(R.id.back);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View backButton) {
                onBackPressed();
            }
        });

        noteEditText = findViewById(R.id.note);
        titleEditText = findViewById(R.id.title);
        notesDatabase = NotesDatabase.getDatabase(this);
    }

    private void saveNote() {
        String note = noteEditText.getText().toString();
        String title = titleEditText.getText().toString();

        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setContent(note);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                notesDatabase.noteDao().insert(newNote);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNote();
    }
}

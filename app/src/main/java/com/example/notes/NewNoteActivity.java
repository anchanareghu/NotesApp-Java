package com.example.notes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
public class NewNoteActivity extends AppCompatActivity {
    private EditText noteEditText;
    private EditText titleEditText;
    private NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes);

        getSupportActionBar().hide();

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

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesDatabase.noteDao().insert(newNote);
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

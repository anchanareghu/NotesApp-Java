package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

public class NewNoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes);
    }

    private void saveNote() {
        EditText noteEditText = findViewById(R.id.note);
        EditText titleEditText = findViewById(R.id.title);

        String note = noteEditText.getText().toString();
        String title = titleEditText.getText().toString();

        SharedPreferences preferences = getSharedPreferences("MyNotesPrefs", MODE_PRIVATE);
        Set<String> notesSet = preferences.getStringSet("notes", new HashSet<>());

        String newNote = title + "\n" + note;
        notesSet.add(newNote);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("notes", notesSet);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNote();
    }
}




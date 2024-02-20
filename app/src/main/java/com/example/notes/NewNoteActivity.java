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
    static EditText noteEditText;
    static EditText titleEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes);


        View back_icon = findViewById(R.id.back);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View backButton) {
                Intent intent = new Intent(backButton.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        noteEditText = findViewById(R.id.note);
        titleEditText = findViewById(R.id.title);
    }

    private void saveNote() {
        String note = noteEditText.getText().toString();
        String title = titleEditText.getText().toString();

        // Load existing notes
        SharedPreferences preferences = getSharedPreferences("MyNotesPrefs", MODE_PRIVATE);
        Set<String> defaultSet = new HashSet<>();
        Set<String> notesSet = new HashSet<>(preferences.getStringSet("notes", defaultSet));

        // Add new note
        String newNote = title + "\n" + note;
        notesSet.add(newNote);

        // Save updated notes
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("notes", notesSet);
        editor.apply();

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNote();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

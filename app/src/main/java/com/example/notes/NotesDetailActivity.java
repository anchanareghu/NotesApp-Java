package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class NotesDetailActivity extends Activity {
    EditText editTitle;
    EditText editNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("note_title");
        String content = intent.getStringExtra("note_content");

        editTitle = (EditText) findViewById(R.id.saved_title);
        editNote = (EditText) findViewById(R.id.saved_note);

        editTitle.setText(title);
        editNote.setText(content);
        View back_icon = findViewById(R.id.back);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View backButton) {
                Intent intent = new Intent(backButton.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}



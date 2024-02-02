package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class NotesDetailActivity extends Activity {
    EditText editTitleView;
    EditText editNoteView;
    int notePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("note_title");
        String content = intent.getStringExtra("note_content");
        notePosition = intent.getIntExtra("note_position", -1);

        editTitleView = (EditText) findViewById(R.id.saved_title);
        editNoteView = (EditText) findViewById(R.id.saved_note);

        editTitleView.setText(title);
        editNoteView.setText(content);

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



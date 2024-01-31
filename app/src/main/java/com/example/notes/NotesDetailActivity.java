package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class NotesDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notes_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("note_title");
        String content = intent.getStringExtra("note_content");

        TextView textViewTitle = findViewById(R.id.saved_title);
        TextView textViewContent = findViewById(R.id.saved_note);

        textViewTitle.setText(title);
        textViewContent.setText(content);
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



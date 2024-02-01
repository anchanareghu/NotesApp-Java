package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;


public class NotesDetailActivity extends Activity {
    EditText editTitle;
    EditText editNote;
    RecyclerViewAdapter adapter;

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

    private void updateNote() {
        String updatedTitle = editTitle.getText().toString();
        String updatedContent = editNote.getText().toString();

        // Update the note data
        String updatedNote = updatedTitle + "\n" + updatedContent;


        // Pass the updated note back to the MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("updated_note", updatedNote);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    @Override
    public void onBackPressed() {
        updateNote();
        super.onBackPressed();
    }
}



package com.example.notes;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends Activity {
    static ArrayList<String> notesList;
    static ArrayList<String> titleList;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View add_note = findViewById(R.id.add_button);
        View add_image = findViewById(R.id.add_image);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(notesList, this);
        recyclerView.setAdapter(adapter);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        SearchView searchView = findViewById(R.id.search_notes);


        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View add_view) {
                Intent intent = new Intent(add_view.getContext(), NewNoteActivity.class);
                startActivity(intent);
            }
        });
        AddImageOnClick addImageOnClick = new AddImageOnClick(new NewImageNoteActivity());
        add_image.setOnClickListener(addImageOnClick);

    }


    private void loadNotes() {
        SharedPreferences preferences = getSharedPreferences("MyNotesPrefs", MODE_PRIVATE);
        Set<String> defaultSet = new HashSet<>();
        Set<String> defaultTitleSet = new HashSet<>();
        notesList = new ArrayList<>(preferences.getStringSet("notes", defaultSet));
        titleList = new ArrayList<>(preferences.getStringSet("title", defaultTitleSet));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
        adapter.updateData(notesList, titleList);
    }
}

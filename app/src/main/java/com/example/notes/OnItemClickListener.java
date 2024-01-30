package com.example.notes;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class OnItemClickListener implements View.OnClickListener {
    ArrayList<String> notesList;
    int position;

    public OnItemClickListener(ArrayList<String> notesList, int position) {
        this.notesList = notesList;
        this.position = position;
    }


    //    @Override
//    public void onItemClick(View view, int position) {
//        String fullNote = notesList.get(position);
////            showToast("Clicked: " + fullNote);
//    }
//
//    @Override
//    public void onLongItemClick(View view, int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//        builder.setTitle("Delete Note");
//        builder.setMessage("Are you sure you want to delete this note?");
////            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
////                @Override
////                public void onClick(DialogInterface dialog, int which) {
////                    deleteNote(position,view);
//    }
//
    @Override
    public void onClick(View v) {
//        String fullNote = notesList.get(position);
//        Intent intent = new Intent(v.getContext(), NewNoteActivity.class);
//        intent.putExtra("note", note);
//        intent.putExtra("position", position);
//        startActivity(intent);

    }
}

//            });
//            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            builder.show();
//        }
//
//    }
//
//    private void deleteNote(int position, View view) {
//        notesList.remove(position);
//        titleList.remove(position);
//
//        // Save updated notes to SharedPreferences
//        SharedPreferences preferences = view.getContext().getSharedPreferences("MyNotesPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putStringSet("notes", new HashSet<>(notesList));
//        editor.apply();
//
//        // Notify the adapter that the data has changed
//        RecyclerViewAdapter adapter = null;
//        adapter.updateData(notesList, titleList);
//    }
//}




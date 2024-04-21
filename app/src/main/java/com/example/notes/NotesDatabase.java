package com.example.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static volatile NotesDatabase INSTANCE;

    public static NotesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    NotesDatabase.class, "notes_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

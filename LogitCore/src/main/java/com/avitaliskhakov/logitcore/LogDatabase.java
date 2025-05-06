package com.avitaliskhakov.logitcore;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LogEntry.class}, version = 1)
public abstract class LogDatabase extends RoomDatabase {
    private static LogDatabase instance;

    public abstract LogDao logDao();

    public static synchronized LogDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LogDatabase.class, "log_database").build();
        }
        return instance;
    }
}


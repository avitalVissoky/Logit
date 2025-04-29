package com.example.logitcore;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogDao {
    @Insert
    void insert(LogEntry entry);

    @Query("SELECT * FROM logs ORDER BY timestamp DESC")
    List<LogEntry> getAllLogsByApp();

    @Query("DELETE FROM logs")
    void deleteAll();
}


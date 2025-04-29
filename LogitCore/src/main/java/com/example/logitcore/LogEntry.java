package com.example.logitcore;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logs")
public class LogEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public long timestamp;
    public LogLevel level;
    public String tag;
    public String message;

    public LogEntry(LogLevel level, String tag, String message) {
        this.timestamp = System.currentTimeMillis();
        this.level = level;
        this.tag = tag;
        this.message = message;
    }
}

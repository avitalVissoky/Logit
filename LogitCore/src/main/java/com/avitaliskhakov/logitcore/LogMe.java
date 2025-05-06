package com.avitaliskhakov.logitcore;

import android.content.Context;

public abstract class LogMe {

    private static LogDao logDao;

    public static void init(Context context) {
        logDao = LogDatabase.getInstance(context).logDao();
    }

    public static void log(LogLevel level, String tag, String message) {
        LogEntry entry = new LogEntry(level, tag, message);
        new Thread(() -> logDao.insert(entry)).start();
        android.util.Log.println(mapLevelToAndroid(level), tag, message);
    }

    public static void debug(String tag, String message) {
        log(LogLevel.DEBUG, tag, message);
    }

    public static void warning(String tag, String message) {
        log(LogLevel.WARNING, tag, message);
    }

    public static void info(String tag, String message) {
        log(LogLevel.INFO, tag, message);
    }
    public static void error(String tag, String message) {
        log(LogLevel.ERROR, tag, message);
    }

    private static int mapLevelToAndroid(LogLevel level) {
        switch (level) {
            case DEBUG: return android.util.Log.DEBUG;
            case INFO: return android.util.Log.INFO;
            case WARNING: return android.util.Log.WARN;
            case ERROR: return android.util.Log.ERROR;
            default: return android.util.Log.VERBOSE;
        }
    }
}

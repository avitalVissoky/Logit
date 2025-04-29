package com.example.logitcore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    private final List<LogEntry> logs = new ArrayList<>();

    public void setLogs(List<LogEntry> newLogs) {
        logs.clear();
        logs.addAll(newLogs);
        notifyDataSetChanged();
    }

    public List<LogEntry> getCurrentLogs() {
        return logs;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log_entry, parent, false);
        return new LogViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        LogEntry log = logs.get(position);
        holder.level.setText(log.level.name());
        holder.tag.setText(log.tag);
        holder.message.setText(log.message);

        String date = new SimpleDateFormat("HH:mm:ss dd/MM", Locale.getDefault()).format(new Date(log.timestamp));
        holder.timestamp.setText(date);

        int color = getColorForLevel(log.level, holder.itemView.getContext());
        holder.level.setTextColor(color);
    }

    private int getColorForLevel(LogLevel level, Context context) {
        switch (level) {
            case DEBUG:
                return context.getResources().getColor(android.R.color.holo_blue_light, context.getTheme());
            case INFO:
                return context.getResources().getColor(android.R.color.holo_green_light, context.getTheme());
            case WARNING:
                return context.getResources().getColor(android.R.color.holo_orange_light, context.getTheme());
            case ERROR:
                return context.getResources().getColor(android.R.color.holo_red_light, context.getTheme());
            default:
                return context.getResources().getColor(android.R.color.darker_gray, context.getTheme());
        }
    }


    @Override
    public int getItemCount() {
        return logs.size();
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView level, tag, message, timestamp;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            level = itemView.findViewById(R.id.logLevel);
            tag = itemView.findViewById(R.id.logTag);
            message = itemView.findViewById(R.id.logMessage);
            timestamp = itemView.findViewById(R.id.logTime);
        }
    }
}

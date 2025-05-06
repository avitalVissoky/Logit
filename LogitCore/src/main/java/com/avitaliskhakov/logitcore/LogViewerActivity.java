package com.avitaliskhakov.logitcore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class LogViewerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private Button clearBtn, shareBtn;
    private Spinner filterSpinner;
    private List<LogEntry> allLogs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_viewer);

        initViews();
        setupRecyclerView();
        setupListeners();
        loadLogs();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.logRecyclerView);
        clearBtn = findViewById(R.id.clearButton);
        shareBtn = findViewById(R.id.shareButton);
        filterSpinner = findViewById(R.id.filterSpinner);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LogAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void setupListeners() {
        clearBtn.setOnClickListener(v -> clearLogs());
        shareBtn.setOnClickListener(v -> shareLogs());

        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterLogs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void loadLogs() {
        new Thread(() -> {
            allLogs = LogDatabase.getInstance(this).logDao().getAllLogsByApp();
            runOnUiThread(this::filterLogs);
        }).start();
    }


    private void filterLogs() {
        String selected = (String) filterSpinner.getSelectedItem();
        List<LogEntry> filtered = new ArrayList<>();

        if ("All".equals(selected)) {
            filtered.addAll(allLogs);
        } else {
            for (LogEntry log : allLogs) {
                if (log.level.name().equals(selected)) {
                    filtered.add(log);
                }
            }
        }

        adapter.setLogs(filtered);
    }

    private void clearLogs() {
        new Thread(() -> {
            LogDatabase.getInstance(this).logDao().deleteAll();
            allLogs.clear();
            runOnUiThread(this::filterLogs);
        }).start();
    }

    private void shareLogs() {
        List<LogEntry> logsToShare = adapter.getCurrentLogs();
        StringBuilder sb = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM", Locale.getDefault());
        for (LogEntry log : logsToShare) {
            String time = sdf.format(new Date(log.timestamp));
            sb.append(time)
                    .append(" [").append(log.level).append("] ")
                    .append(log.tag).append(": ")
                    .append(log.message)
                    .append("\n");
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, sb.toString());
        startActivity(Intent.createChooser(intent, "share logs..."));
    }
}

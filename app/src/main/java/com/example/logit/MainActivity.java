package com.example.logit;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.logitcore.LogViewerActivity;
import com.example.logitcore.LogMe;

public class MainActivity extends AppCompatActivity {

    private Button debugBtn, errorBtn, warningBtn, infoBtn, viewLogsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogMe.init(getApplicationContext());

        debugBtn = findViewById(R.id.debugButton);
        errorBtn = findViewById(R.id.errorButton);
        warningBtn = findViewById(R.id.warningButton);
        infoBtn = findViewById(R.id.infoButton);
        viewLogsBtn = findViewById(R.id.viewLogsButton);

        debugBtn.setOnClickListener(v -> LogMe.debug("MainActivity", "this is a debug log"));
        errorBtn.setOnClickListener(v -> LogMe.error( "MainActivity", "this is a warning log"));
        infoBtn.setOnClickListener(v -> LogMe.info("MainActivity", "this is an error log"));
        warningBtn.setOnClickListener(v -> LogMe.warning("MainActivity", "this is an info log"));

        viewLogsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LogViewerActivity.class);
            startActivity(intent);
        });
    }
}
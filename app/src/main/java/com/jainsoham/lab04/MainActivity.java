package com.jainsoham.lab04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    int idx = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        handler = new Handler(Looper.myLooper());
        scheduleLayoutTransition();
    }

    private void scheduleLayoutTransition() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switchLayoutWithTransition();
                scheduleLayoutTransition();
            }
        }, 5000);
    }

    private void switchLayoutWithTransition() {
        idx = (idx % 8) + 1;
        int layoutResourceId = getResources().getIdentifier("layout" + idx, "layout", getPackageName());
        ViewGroup rootLayout = findViewById(android.R.id.content);
        AutoTransition autoTransition = new AutoTransition();
        TransitionManager.beginDelayedTransition(rootLayout, autoTransition);
        setContentView(layoutResourceId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}

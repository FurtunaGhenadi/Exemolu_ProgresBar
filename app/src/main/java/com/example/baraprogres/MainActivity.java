package com.example.baraprogres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    static int progres;
    ProgressBar progresBar;
    int progresStatus = 0;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progres = 0;
        progresBar = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progresStatus < 10) {
                    progresStatus = doSomeWork();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progresBar.setVisibility(View.GONE);
                    }
                });
            }
            private int doSomeWork() {
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return ++progres;
            }
        }).start();

    }

}
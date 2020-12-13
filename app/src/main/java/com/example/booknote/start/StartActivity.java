package com.example.booknote.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.booknote.Bookshelf_Activity;
import com.example.booknote.MainActivity;
import com.example.booknote.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        StartThread startThread = new StartThread(handler);
        startThread.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                Intent intent = new Intent(StartActivity.this, Bookshelf_Activity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}

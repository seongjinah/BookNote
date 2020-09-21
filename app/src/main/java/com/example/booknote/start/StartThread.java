package com.example.booknote.start;

import android.os.Handler;
import android.os.Message;

public class StartThread extends Thread {

    private Handler handler;

    public StartThread(Handler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();

        Message msg = new Message();

        try{
            Thread.sleep(1500);
            msg.what = 1;
            handler.sendEmptyMessage(msg.what);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
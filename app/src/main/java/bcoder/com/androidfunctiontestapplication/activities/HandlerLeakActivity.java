package bcoder.com.androidfunctiontestapplication.activities;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bcoder.com.androidfunctiontestapplication.R;

public class HandlerLeakActivity extends AppCompatActivity {
    private LeakHandler mLeakHandler;
    private NoLeakHandler mNoLeakHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_leak);
    }

    class LeakHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    static class NoLeakHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}

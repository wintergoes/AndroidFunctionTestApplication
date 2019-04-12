package bcoder.com.androidfunctiontestapplication.activities;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

import bcoder.com.androidfunctiontestapplication.R;

public class HandlerLeakActivity extends AppCompatActivity {
    private static final String LOG_TAG = "HandlerLeakActivity";

    private LeakHandler mLeakHandler;
    private NoLeakHandler mNoLeakHandler;

    private static final int MSG_ONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_leak);

        mLeakHandler = new LeakHandler();
        mNoLeakHandler = new NoLeakHandler(this);
    }

    @Override
    protected void onDestroy() {
        // remove掉消息后也不会有内存泄漏　
//        mLeakHandler.removeMessages(MSG_ONE);
        super.onDestroy();
    }

    public void onBtnLeakHandlerClick(View view) {
        ((Button)view).setEnabled(false);
        mLeakHandler.sendEmptyMessageDelayed(MSG_ONE, 2000);
    }

    public void onBtnNoLeakHandlerClick(View view) {
        ((Button)view).setEnabled(false);
        mNoLeakHandler.sendEmptyMessageDelayed(MSG_ONE, 2000);
    }

    class LeakHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_ONE:
                    mLeakHandler.sendEmptyMessageDelayed(MSG_ONE, 2000);
                    Log.i(LOG_TAG, "LeakHandler get MSG_ONE.");
                    break;
            }
        }
    }

    static class NoLeakHandler extends Handler{
        WeakReference<HandlerLeakActivity> activity;

        public NoLeakHandler(HandlerLeakActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_ONE:
                    if(activity.get() != null) {
                        activity.get().mNoLeakHandler.sendEmptyMessageDelayed(MSG_ONE, 2000);
                    } else {
                        Log.i(LOG_TAG, "Activity is null now.");
                    }
                    Log.i(LOG_TAG, "NoLeakHandler get MSG_ONE.");
                    break;
            }
        }
    }
}

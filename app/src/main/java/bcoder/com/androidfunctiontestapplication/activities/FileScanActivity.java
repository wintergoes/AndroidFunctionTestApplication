package bcoder.com.androidfunctiontestapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import bcoder.com.androidfunctiontestapplication.R;
import bcoder.com.androidfunctiontestapplication.utils.FileScanner;

public class FileScanActivity extends AppCompatActivity {
    private boolean isScanByWhileRunning = false;
    private static final String LOG_TAG = "FileScanActivity";

    private static final String SCAN_DIR = "/sdcard";

    private static final int SCAN_COUNT = 100;

    ScanByWhileThread scanByWhileThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_scan);
    }

    public void onScanFileByWhileLoop(View view) {
        scanByWhileThread = new ScanByWhileThread();
        scanByWhileThread.start();
    }

    public void onScanByWhileClick(View view) {
        if(scanByWhileThread == null){
            Toast.makeText(this, "扫描线程为null！", Toast.LENGTH_LONG).show();
            return;
        }
        scanByWhileThread.stopScan();
    }

    class ScanByWhileThread extends Thread{
        private FileScanner fileScanner = new FileScanner();

        public void stopScan(){
            fileScanner.stopScan();
        }

        @Override
        public void run() {
            super.run();

            long starttime = System.currentTimeMillis();
            for (int i = 0; i < SCAN_COUNT; i++) {
                Log.d(LOG_TAG, String.format("Test No.: %d", i));
                fileScanner.scanFiles(SCAN_DIR);
            }
            long timeuse = System.currentTimeMillis() - starttime;
            Log.d(LOG_TAG, String.format("TimeUse: %d", timeuse));
        }
    }
}

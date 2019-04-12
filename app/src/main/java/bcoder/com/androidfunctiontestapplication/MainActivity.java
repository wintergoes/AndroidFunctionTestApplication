package bcoder.com.androidfunctiontestapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bcoder.com.androidfunctiontestapplication.activities.FileScanActivity;
import bcoder.com.androidfunctiontestapplication.activities.HandlerLeakActivity;
import bcoder.com.androidfunctiontestapplication.activities.LayoutWeightTestActivity;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void onOpenFileScanTest(View view) {
        Intent intent = new Intent(this, FileScanActivity.class);
        startActivity(intent);
    }

    public void onHandlerLeakTestClick(View view) {
        Intent intent = new Intent(this, HandlerLeakActivity.class);
        startActivity(intent);
    }

    public void onLayoutWeightTestClick(View view) {
        Intent intent = new Intent(this, LayoutWeightTestActivity.class);
        startActivity(intent);
    }
}

package bcoder.com.androidfunctiontestapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bcoder.com.androidfunctiontestapplication.R;

public class LayoutWeightTestActivity extends AppCompatActivity {
    ImageView mImageView;
    LinearLayout mLinearLayout;
    LinearLayout.LayoutParams mLayoutParams;
    TextView mTvCOlor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_weight_test);

    }


}

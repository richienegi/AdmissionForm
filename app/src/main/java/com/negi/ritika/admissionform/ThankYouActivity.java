package com.negi.ritika.admissionform;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThankYouActivity extends AppCompatActivity {

    @BindView(R.id.admin_use)
    TextView admin_use;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                admin_use.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

    public void admin_panel(View view) {
        
    }
}

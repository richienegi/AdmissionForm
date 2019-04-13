package com.negi.ritika.admissionform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.negi.ritika.admissionform.Model_Class.DataClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeesActivity extends AppCompatActivity {

    @BindView(R.id.back)
    Button back;
    @BindView(R.id.submit)
    Button submit;

    @BindView(R.id.total_fees)
    EditText total_fees;
    @BindView(R.id.reg_fees)
    EditText reg_fees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);

        ButterKnife.bind(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fee1 = total_fees.getText().toString().trim();
                String fee2 = reg_fees.getText().toString().trim();

                DataClass.total_fees = fee1;
                DataClass.reg_fees = fee2;

                startActivity(new Intent(FeesActivity.this, NinthActivity.class));
            }
        });
    }
}

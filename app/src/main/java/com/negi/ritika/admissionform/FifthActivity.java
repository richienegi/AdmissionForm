package com.negi.ritika.admissionform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.negi.ritika.admissionform.Model_Class.DataClass;
import com.negi.ritika.admissionform.Model_Class.ToggleButtonGroupTableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FifthActivity extends AppCompatActivity {

    Button mnext, mback, mskip;
    RadioGroup rg;

    @BindView(R.id.company_name)
    EditText company;
    @BindView(R.id.designation)
    EditText designation;
    @BindView(R.id.website)
    EditText website;
    @BindView(R.id.coll_name)
    EditText coll_name;
    @BindView(R.id.coll_address)
    EditText coll_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        ButterKnife.bind(this);

        rg = (RadioGroup) findViewById(R.id.rg_2);
        mskip = (Button) findViewById(R.id.skip);
        mnext = (Button) findViewById(R.id.next);
        mback = (Button) findViewById(R.id.back);
        mskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataClass.passout_or_student = "";
                DataClass.company_name = "";
                DataClass.designation = "";
                DataClass.website = "";
                DataClass.college_name = "";
                DataClass.college_address = "";
                int select = rg.getCheckedRadioButtonId();
                RadioButton rb;
                if (select > -1) {
                    rb = findViewById(select);
                    DataClass.passout_or_student = rb.getText().toString();
                } else {
                    DataClass.passout_or_student = "";
                }
                Intent i = new Intent(FifthActivity.this, SixthActivity.class);
                startActivity(i);
            }
        });
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int select = rg.getCheckedRadioButtonId();
                RadioButton rb;
                if (select > -1) {
                    rb = findViewById(select);
                    DataClass.passout_or_student = rb.getText().toString();
                } else {
                    DataClass.passout_or_student = "";
                }

                DataClass.company_name = company.getText().toString();
                DataClass.designation = designation.getText().toString();
                DataClass.website = website.getText().toString();
                DataClass.college_name = coll_name.getText().toString();
                DataClass.college_address = coll_address.getText().toString();

                Log.d(ToggleButtonGroupTableLayout.TAG, DataClass.company_name+"\n"+DataClass.designation+"\n"+DataClass.website+"\n"+DataClass.college_name+"\n"+DataClass.college_address);
                Intent i = new Intent(FifthActivity.this, SixthActivity.class);
                startActivity(i);
            }
        });
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

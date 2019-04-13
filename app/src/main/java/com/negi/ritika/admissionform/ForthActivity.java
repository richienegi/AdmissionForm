package com.negi.ritika.admissionform;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.negi.ritika.admissionform.Model_Class.DataClass;
import com.negi.ritika.admissionform.Model_Class.ToggleButtonGroupTableLayout;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForthActivity extends AppCompatActivity {

    Button mnext, mback;

    @BindView(R.id.quali) EditText quali;
    @BindView(R.id.school) EditText school;
    @BindView(R.id.pass_year) EditText passing_year;

    @OnClick(R.id.pass_year)
    public void setPassingDate()
    {
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(ForthActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                passing_year.setText(selectedday + "-" + DataClass.month[selectedmonth] + "-" + selectedyear);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.updateDate(2000, 1, 1);
        mDatePicker.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        ButterKnife.bind(this);

        mnext = (Button) findViewById(R.id.next);
        mback = (Button) findViewById(R.id.back);
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = quali.getText().toString();
                String s = school.getText().toString();
                String p = passing_year.getText().toString();

                if(q.isEmpty())
                {
                    quali.setError("Please Fill this Field");
                    quali.requestFocus();
                    return;
                }
                if(s.isEmpty())
                {
                    s = "";
                }
                if(p.isEmpty())
                {
                    p="";
                }

                DataClass.user_qualification = q;
                DataClass.user_school = s;
                DataClass.user_passing = p;

                Log.d(ToggleButtonGroupTableLayout.TAG, DataClass.user_qualification+"\n"+DataClass.user_school+"\n"+DataClass.user_passing);

                Intent i = new Intent(ForthActivity.this, FifthActivity.class);
                startActivity(i);
            }
        });
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClass.user_qualification = "";
                DataClass.user_school = "";
                DataClass.user_passing = "";
                finish();
            }
        });
    }
}

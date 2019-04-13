package com.negi.ritika.admissionform;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.negi.ritika.admissionform.Model_Class.DataClass;
import com.negi.ritika.admissionform.Model_Class.ToggleButtonGroupTableLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText first, mid, last, Ffirst, Flast, Fmid, Mfirst, Mmid, Mlast, dobirth;
    RadioGroup gender;
    Button next;
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dobirth = (EditText) findViewById(R.id.dob);
        first = (EditText) findViewById(R.id.mfirst);
        mid = (EditText) findViewById(R.id.mmid);
        last = (EditText) findViewById(R.id.mlast);
        Ffirst = (EditText) findViewById(R.id.mFfirst);
        Fmid = (EditText) findViewById(R.id.mFmid);
        Flast = (EditText) findViewById(R.id.mFlast);
        Mfirst = (EditText) findViewById(R.id.mMfirst);
        Mmid = (EditText) findViewById(R.id.mMmid);
        Mlast = (EditText) findViewById(R.id.mFlast);
        next = (Button) findViewById(R.id.mnext);

        gender = (RadioGroup) findViewById(R.id.mgender);

        dobirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        dobirth.setText(selectedday + "-" + DataClass.month[selectedmonth] + "-" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.updateDate(1990, 1, 1);
                mDatePicker.show();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //person name
                String fname = first.getText().toString();
                String ffname = Ffirst.getText().toString();
                String mfname = Mfirst.getText().toString();
                String dob = dobirth.getText().toString();

                if (TextUtils.isEmpty(fname)) {
                    first.setError("This field is required");
                    first.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ffname)) {
                    Ffirst.setError("This field is required");
                    Ffirst.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mfname)) {
                    Mfirst.setError("This field is required");
                    Mfirst.requestFocus();

                    return;
                }
                if (TextUtils.isEmpty(dob)) {
                    dobirth.setError("This Field is Required");
                    dobirth.requestFocus();
                    return;
                }
                if (gender.getCheckedRadioButtonId() == -1) {
                    //Creating the LayoutInflater instance
                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast, (ViewGroup) findViewById(R.id.custom_toast_layout));
                    TextView tv = (TextView) layout.findViewById(R.id.custom_toast_message);
                    tv.setText("Please Select your Gender");
                    Toast toast = new Toast(MainActivity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
                    Toast.makeText(MainActivity.this, "Please selecte gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                int id = gender.getCheckedRadioButtonId();
                RadioButton rb = findViewById(id);

                DataClass.user_f_name = fname;
                DataClass.user_m_name = mid.getText().toString();
                DataClass.user_l_name = last.getText().toString();

                //fathers name
                DataClass.father_f_name = ffname;
                DataClass.father_m_name = Fmid.getText().toString();
                DataClass.father_l_name = Flast.getText().toString();

                //mothers name
                DataClass.mother_f_name = mfname;
                DataClass.mother_m_name = Mmid.getText().toString();
                DataClass.mother_l_name = Mlast.getText().toString();

                DataClass.user_dob = dob;
                DataClass.user_gender = rb.getText().toString();

                Log.d(ToggleButtonGroupTableLayout.TAG,
                        DataClass.user_f_name+"\n"+
                                DataClass.user_m_name+"\n"+
                                DataClass.user_l_name+"\n"+
                                DataClass.father_f_name+"\n"+
                                DataClass.father_m_name+"\n"+
                                DataClass.father_l_name+"\n"+
                                DataClass.mother_f_name+"\n"+
                                DataClass.mother_m_name+"\n"+
                                DataClass.mother_l_name+"\n"+
                                DataClass.user_dob);


                Intent i = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(i);
            }
        });


    }
}

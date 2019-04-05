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

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText first,mid,last,Ffirst,Flast,Fmid,Mfirst,Mmid,Mlast,dobirth;
    RadioGroup gender;
    Button next;
    static String fname="",mname="",lname="",ffname="",fmname="",flname="",mfname="",mmname="",mlname="",genderr="",dateofb="";
    int mYear,mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dobirth=(EditText)findViewById(R.id.dob);
        first=(EditText)findViewById(R.id.mfirst);
        mid=(EditText)findViewById(R.id.mmid);
        last=(EditText)findViewById(R.id.mlast);
        Ffirst=(EditText)findViewById(R.id.mFfirst);
        Fmid=(EditText)findViewById(R.id.mFmid);
        Flast=(EditText)findViewById(R.id.mFlast);
        Mfirst=(EditText)findViewById(R.id.mMfirst);
        Mmid=(EditText)findViewById(R.id.mMmid);
        Mlast=(EditText)findViewById(R.id.mFlast);
        next=(Button)findViewById(R.id.mnext);

        gender=(RadioGroup)findViewById(R.id.mgender);
        //c1=(CardView)findViewById(R.id.card1);
        dobirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        dobirth.setText(selectedyear+"-"+(selectedmonth+1)+"-"+selectedday);

                        /*      Your code   to get date and time    */
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                //person name
                fname=first.getText().toString();
                mname=mid.getText().toString();
                lname=last.getText().toString();

                //fathers name
                ffname=Ffirst.getText().toString();
                fmname=Fmid.getText().toString();
                flname=Flast.getText().toString();

                //mothers name
                mfname=Mfirst.getText().toString();
                mmname=Mmid.getText().toString();
                mlname=Mlast.getText().toString();

                dateofb=dobirth.getText().toString();

                if(TextUtils.isEmpty(fname)) {
                    first.setError("This field is required");
                    first.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(ffname)) {
                    Ffirst.setError("This field is required");
                    Ffirst.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(mfname)) {
                    Mfirst.setError("This field is required");
                    Mfirst.requestFocus();

                    return;
                }
                else if(TextUtils.isEmpty(dobirth.getText()))
                {
                    dobirth.setError("This Field is Required");
                    dobirth.requestFocus();
                    return;
                }
                else if (gender.getCheckedRadioButtonId()==-1) {
                    //Creating the LayoutInflater instance
                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast,(ViewGroup)findViewById(R.id.custom_toast_layout));
                    TextView tv=(TextView)layout.findViewById(R.id.custom_toast_message);
                    tv.setText("Please select your gender");
                    Toast toast = new Toast(MainActivity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
                    Toast.makeText(MainActivity.this, "Please selecte gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {*/
                    Intent i=new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(i);

//                }
            }
        });


    }
}

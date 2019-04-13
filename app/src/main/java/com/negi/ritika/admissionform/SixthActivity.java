package com.negi.ritika.admissionform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.negi.ritika.admissionform.Model_Class.DataClass;
import com.negi.ritika.admissionform.Model_Class.ToggleButtonGroupTableLayout;

public class SixthActivity extends AppCompatActivity {
    Button mnext, mback;

    ToggleButtonGroupTableLayout rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        rg = findViewById(R.id.r_group);

        mnext = (Button) findViewById(R.id.next);
        mback = (Button) findViewById(R.id.back);
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = rg.getCheckedRadioButtonId();
                RadioButton rb;
                if (id == -1) {
                    //Creating the LayoutInflater instance
                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast, (ViewGroup) findViewById(R.id.custom_toast_layout));
                    TextView tv = (TextView) layout.findViewById(R.id.custom_toast_message);
                    tv.setText("Please Select your Choice");
                    Toast toast = new Toast(SixthActivity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
//                    Toast.makeText(SixthActivity.this, "Please selecte gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                rb = findViewById(id);
                DataClass.source = rb.getText().toString();

                Log.d(ToggleButtonGroupTableLayout.TAG, DataClass.source);
                Intent i = new Intent(SixthActivity.this, SeventhActivity.class);
                startActivity(i);
            }
        });
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClass.source = "";
                finish();
            }
        });
    }
}

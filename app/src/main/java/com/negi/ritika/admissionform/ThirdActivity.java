package com.negi.ritika.admissionform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.negi.ritika.admissionform.Model_Class.DataClass;
import com.negi.ritika.admissionform.Model_Class.ToggleButtonGroupTableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThirdActivity extends AppCompatActivity {

    Button mnext, mback;

    @BindView(R.id.edit_mobile)
    EditText mob_no;
    @BindView(R.id.edit_whatsapp)
    EditText whatsapp_no;
    @BindView(R.id.edit_alternate)
    EditText alter_no;
    @BindView(R.id.edit_email)
    EditText email;
    @BindView(R.id.edit_father_no)
    EditText father_no;
    @BindView(R.id.edit_mother_no)
    EditText mother_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);

        mnext = (Button) findViewById(R.id.next);
        mback = (Button) findViewById(R.id.back);
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClass.user_mobile = "";
                DataClass.user_whatsapp = "";
                DataClass.user_alternate = "";
                DataClass.user_email = "";
                DataClass.father_mobile = "";
                DataClass.mother_mobile = "";
                finish();
            }
        });
    }

    private void setData() {
        String num = mob_no.getText().toString();
        String w_num = whatsapp_no.getText().toString();
        String al_num = alter_no.getText().toString();
        String em = email.getText().toString();
        String fnum = father_no.getText().toString();
        String mnum = mother_no.getText().toString();

        if (num.isEmpty()) {
            mob_no.setError("Please Fill this Field");
            mob_no.requestFocus();
            return;
        }
        if (w_num.isEmpty()) {
            whatsapp_no.setError("Please Fill this Field");
            whatsapp_no.requestFocus();
            return;
        }
        if (al_num.isEmpty()) {
            al_num="";
        }
        if (em.isEmpty()) {
            em="";
        }
        if (fnum.isEmpty()) {
            fnum = "";
        }
        if (mnum.isEmpty()) {
            mnum = "";
        }
        DataClass.user_mobile = num;
        DataClass.user_whatsapp = w_num;
        DataClass.user_alternate = al_num;
        DataClass.user_email = em;
        DataClass.father_mobile = fnum;
        DataClass.mother_mobile = mnum;

        Log.d(ToggleButtonGroupTableLayout.TAG, DataClass.user_mobile+"\n"+DataClass.user_whatsapp+"\n"+DataClass.user_alternate+"\n"+DataClass.user_email+"\n"+DataClass.father_mobile+"\n"+DataClass.mother_mobile);

        Intent i = new Intent(ThirdActivity.this, SecondActivity.class);
        startActivity(i);
    }
}

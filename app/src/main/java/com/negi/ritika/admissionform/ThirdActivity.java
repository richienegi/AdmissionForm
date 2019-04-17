package com.negi.ritika.admissionform;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.goodiebag.pinview.Pinview;
import com.negi.ritika.admissionform.Model_Class.DataClass;
import com.negi.ritika.admissionform.Model_Class.ToggleButtonGroupTableLayout;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThirdActivity extends AppCompatActivity {

    Button mnext, mback;

    @BindView(R.id.edit_mobile)
    EditText mob_no;
    @BindView(R.id.edit_whatsapp)
    EditText whatsapp_no;
    @BindView(R.id.edit_address)
    EditText address;
    @BindView(R.id.edit_email)
    EditText email;
    @BindView(R.id.edit_father_no)
    EditText father_no;
    @BindView(R.id.edit_mother_no)
    EditText mother_no;

    private static String otpnumber = "";
    String otp="";

    AlertDialog ad;

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
                DataClass.user_address = "";
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
        String add = address.getText().toString();
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
        if (add.isEmpty()) {
            add = "";
        }
        if (em.isEmpty()) {
            em = "";
        }
        if (fnum.isEmpty()) {
            fnum = "";
        }
        if (mnum.isEmpty()) {
            mnum = "";
        }
        DataClass.user_mobile = num;
        DataClass.user_whatsapp = w_num;
        DataClass.user_address = add;
        DataClass.user_email = em;
        DataClass.father_mobile = fnum;
        DataClass.mother_mobile = mnum;

        Log.d(ToggleButtonGroupTableLayout.TAG, DataClass.user_mobile + "\n" + DataClass.user_whatsapp + "\n" + DataClass.user_address + "\n" + DataClass.user_email + "\n" + DataClass.father_mobile + "\n" + DataClass.mother_mobile);

        sendOTP();
    }

    private void sendOTP() {
        otpnumber = getRandomNumberString();
        String message = "Your OTP is " + otpnumber;
        sendOTP(DataClass.user_mobile, message);

        showVerificationBox();
    }

    private void showVerificationBox() {
        Toast.makeText(this, ""+otpnumber, Toast.LENGTH_SHORT).show();
        View v = LayoutInflater.from(this).inflate(R.layout.otp_layout, null);

        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setView(v);
        b.setCancelable(false);

        ad = b.create();
        ad.show();

        Pinview p = v.findViewById(R.id.pinView);

        p.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                otp = pinview.getValue();
            }
        });

        TextView num = v.findViewById(R.id.num);
        num.append(DataClass.user_mobile);

        Button sub = v.findViewById(R.id.submit);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp.equals(otpnumber))
                {
                    startActivity(new Intent(ThirdActivity.this, SecondActivity.class));
                    ad.dismiss();
                }
                else
                {
                    Toast.makeText(ThirdActivity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(ad.isShowing())
        {
            ad.dismiss();
        }
        else
        {
            super.onBackPressed();
        }
    }

    //hiiting OTP
    private void sendOTP(String number, String message) {
        Toast.makeText(ThirdActivity.this, "send otp hits", Toast.LENGTH_SHORT).show();

        String url = "http://203.129.225.69/API/WebSMS/Http/v1.0a/index.php?username=cbitss&password=123456&sender=CBitss&to=91" + number + "&message=" + message + "&reqid=1&format={json|text}&route_id=7";

        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainnActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThirdActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue r = Volley.newRequestQueue(this);
        r.add(sr);
    }

    //generating randome Number
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        // this will convert any number sequence into 6 character.
        return String.format("%04d", number);
    }
}

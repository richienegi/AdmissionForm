package com.negi.ritika.admissionform;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.negi.ritika.admissionform.Model_Class.DataClass;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadStatusDelegate;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class NinthActivity extends AppCompatActivity {


    @BindView(R.id.back)
    Button back;
    @BindView(R.id.submit)
    Button submit;

    @BindView(R.id.admin)
    Button home;

    @BindView(R.id.course_list)
    ListView courses;
    @BindView(R.id.profileimage)
    CircleImageView profile_image;

    @BindView(R.id.qualification)
    TextView qualification;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.whatsapp_num)
    TextView whatsapp_num;
    @BindView(R.id.mobile_no)
    TextView mobile_no;
    @BindView(R.id.mother_name)
    TextView mother_name;
    @BindView(R.id.father_name)
    TextView father_name;
    @BindView(R.id.dob)
    TextView dob;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.total_fee)
    TextView total_fee;


    @OnClick(R.id.fees_box)
    public void feesAlert() {
//        Toast.makeText(this, "fees", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.course_box)
    public void courseAlert() {
//        Toast.makeText(this, "course", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.quali_box)
    public void qualificationAlert() {
//        Toast.makeText(this, "quali", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.contact_box)
    public void contactAlert() {
//        Toast.makeText(this, "contacts", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.personal_box)
    public void personalAlert() {
//        Toast.makeText(this, "personal", Toast.LENGTH_SHORT).show();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth);

        ButterKnife.bind(this);

        name.setText(DataClass.user_f_name + " " + DataClass.user_m_name + " " + DataClass.user_l_name);
        gender.setText(DataClass.user_gender);
        dob.setText(DataClass.user_dob);
        father_name.setText(DataClass.father_f_name + " " + DataClass.father_m_name + " " + DataClass.father_l_name);
        mother_name.setText(DataClass.mother_f_name + " " + DataClass.mother_m_name + " " + DataClass.mother_l_name);
        mobile_no.setText(DataClass.user_mobile);
        whatsapp_num.setText(DataClass.user_whatsapp);
        email.setText(DataClass.user_email);
        qualification.setText(DataClass.user_qualification);
        profile_image.setImageBitmap(DataClass.user_image);
        total_fee.setText(DataClass.total_fees);

        ArrayAdapter ad = new ArrayAdapter(this, R.layout.list_layout_last, DataClass.courses);
        courses.setAdapter(ad);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTerms();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdminLogin();
            }
        });
    }

    private void showAdminLogin() {
        View v = LayoutInflater.from(this).inflate(R.layout.admin_login_alert, null);
        final EditText pass = v.findViewById(R.id.admin_pass);
        Button log = v.findViewById(R.id.login);

        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setCancelable(true);
        b.setView(v);

        final AlertDialog ad = b.create();
        ad.show();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = pass.getText().toString().trim();
                if (Integer.parseInt(pin) == 114877) {
                    startActivity(new Intent(NinthActivity.this, AdminActivity.class));
                } else {
                    Toast.makeText(NinthActivity.this, "Enter Correct Pin", Toast.LENGTH_SHORT).show();
                }

                ad.dismiss();
            }
        });
    }

    private void showTerms() {
        submitForm();
    }

    private void submitForm() {
        View v = LayoutInflater.from(this).inflate(R.layout.thankyou_alert, null);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setView(v);
        b.setCancelable(false);

        final AlertDialog ad = b.create();
        ad.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                submit.setVisibility(View.GONE);
                back.setVisibility(View.GONE);
                home.setVisibility(View.VISIBLE);
                ad.dismiss();
            }
        }, 5000);

    }
}

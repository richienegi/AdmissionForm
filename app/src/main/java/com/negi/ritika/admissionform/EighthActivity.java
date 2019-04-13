package com.negi.ritika.admissionform;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.negi.ritika.admissionform.Adapter.my_adapter;
import com.negi.ritika.admissionform.Model_Class.DataClass;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EighthActivity extends AppCompatActivity {

    Button mskip, mback, mscan;
    @BindView(R.id.recyclerView) RecyclerView rv;
    private final int requestCode = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);

        ButterKnife.bind(this);

        mback = (Button) findViewById(R.id.back);
        mskip = (Button) findViewById(R.id.skip);
        mscan = (Button) findViewById(R.id.scan);
        LinearLayoutManager lm = new LinearLayoutManager(EighthActivity.this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(lm);

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClass.docs.clear();
                finish();
            }
        });

        mskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataClass.docs.size()>0)
                {
                    startActivity(new Intent(EighthActivity.this, NinthActivity.class));
                }
            }
        });

        mscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (this.requestCode == requestCode && resultCode == RESULT_OK) {
                mskip.setText("Next");
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                DataClass.docs.add(bitmap);

                if (DataClass.docs.size() > 0) {
                    my_adapter customAdapter = new my_adapter(EighthActivity.this, DataClass.docs);
                    rv.setAdapter(customAdapter);

                }

            }
        }
        catch (Exception e)
        {
            LayoutInflater li = getLayoutInflater();
            //Getting the View object as defined in the customtoast.xml file
            View layout = li.inflate(R.layout.customtoast, (ViewGroup) findViewById(R.id.custom_toast_layout));
            TextView tv = (TextView) layout.findViewById(R.id.custom_toast_message);
            tv.setText("Please try again");
            Toast toast = new Toast(EighthActivity.this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setView(layout);//setting the view of custom toast layout
            toast.show();
        }
    }


}

package com.negi.ritika.admissionform;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecondActivity extends AppCompatActivity {
    CircleImageView mimage;
    Button mbrowse,mnext,mback;
    Bitmap bmp;
    private static final int CAPTURE_PICCODE = 989;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mimage=(CircleImageView)findViewById(R.id.circleimage);
        mbrowse=(Button)findViewById(R.id.browse);
        mnext=(Button)findViewById(R.id.next);
        mback=(Button)findViewById(R.id.back);
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                if (getStringImage(bmp)==null) {

                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast,(ViewGroup)v.findViewById(R.id.custom_toast_layout));
                    TextView tv=(TextView)layout.findViewById(R.id.custom_toast_message);
                    tv.setText("Please Click Your image");
                    Toast toast = new Toast(SecondActivity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
                } else {*/
                    Intent i=new Intent(SecondActivity.this,ThirdActivity.class);
                    startActivity(i);

//                }
            }
        });
        mbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAPTURE_PICCODE );

            }
        });


        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SecondActivity.this,MainActivity.class));
                finish();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_PICCODE) {
            if (resultCode == Activity.RESULT_OK) {

                bmp = (Bitmap) data.getExtras().get("data");
                /*ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);
*/
                mimage.setImageBitmap(bmp);

            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        } catch (Exception e) {
            Toast.makeText(SecondActivity.this, "Please select Image", Toast.LENGTH_SHORT).show();
            return null;
        }

    }
}

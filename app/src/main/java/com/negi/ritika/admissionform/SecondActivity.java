package com.negi.ritika.admissionform;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.negi.ritika.admissionform.Model_Class.DataClass;
import com.negi.ritika.admissionform.Model_Class.ToggleButtonGroupTableLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecondActivity extends AppCompatActivity {

    CircleImageView mimage;
    Button mbrowse, mnext, mback;
    Bitmap bmp;
    private static final int CAPTURE_PICCODE = 989;


    Uri image;
    String mCameraFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mimage = (CircleImageView) findViewById(R.id.circleimage);
        mbrowse = (Button) findViewById(R.id.browse);
        mnext = (Button) findViewById(R.id.next);
        mback = (Button) findViewById(R.id.back);
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getStringImage(bmp) == null) {

                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast, (ViewGroup) v.findViewById(R.id.custom_toast_layout));
                    TextView tv = (TextView) layout.findViewById(R.id.custom_toast_message);
                    tv.setText("Please Click Your image");
                    Toast toast = new Toast(SecondActivity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);

                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
                } else {
                    DataClass.user_image = bmp;
                    Log.d(ToggleButtonGroupTableLayout.TAG, DataClass.user_image.toString());
                    Intent i = new Intent(SecondActivity.this, ForthActivity.class);
                    startActivity(i);

                }
            }
        });
        mbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                Date date = new Date();
                DateFormat df = new SimpleDateFormat("hh-mm-ss");

                String newPicFile = df.format(date) + ".jpg";
                String outPath = "/sdcard/" + newPicFile;
                File outFile = new File(outPath);

                mCameraFileName = outFile.toString();
                Uri outuri = Uri.fromFile(outFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);
                startActivityForResult(intent, CAPTURE_PICCODE);

            }
        });


        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClass.user_image = null;
                finish();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAPTURE_PICCODE) {
                if (data != null) {
                    image = data.getData();
                    mimage.setImageURI(image);
                }
                if (image == null && mCameraFileName != null) {
                    try {
                        image = Uri.fromFile(new File(mCameraFileName));
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
                        bmp = Bitmap.createScaledBitmap(bitmap, 480, 640, true);

                        mimage.setImageBitmap(bmp);

                        Log.d("image_data", "Height: " + bmp.getHeight() + "\n" + "Width: " + bmp.getWidth());
                        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

                File file = new File(mCameraFileName);
                if (!file.exists()) {
                    file.mkdir();
                }
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

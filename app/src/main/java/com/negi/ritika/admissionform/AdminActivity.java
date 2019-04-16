package com.negi.ritika.admissionform;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AdminActivity extends AppCompatActivity {

    private String UPLOAD_URL = "http://fossfoundation.com/AdmissionForm/registration1.php";
    private String strDate = "";

    ArrayList<String> images;

    @BindView(R.id.admin_total_fee)
    EditText total_fees;

    @BindView(R.id.admin_reg_fee)
    EditText reg_fees;

    @BindView(R.id.admin_no_inst)
    EditText installments;

    @BindView(R.id.admin)
    Button home;

    @BindView(R.id.submit)
    Button submit;

    public void admission() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...");
        pd.show();

        String uploadId = UUID.randomUUID().toString();

        try {
            getCurrentDate();
            new MultipartUploadRequest(AdminActivity.this, uploadId, UPLOAD_URL)
                    .addParameter("name", DataClass.user_f_name + " " + DataClass.user_m_name + " " + DataClass.user_l_name)
                    .addParameter("gender", DataClass.user_gender)
                    .addParameter("dob", DataClass.user_dob)
                    .addParameter("fathername", DataClass.father_f_name + " " + DataClass.father_m_name + " " + DataClass.father_l_name)
                    .addParameter("mothername", DataClass.mother_f_name + " " + DataClass.mother_m_name + " " + DataClass.mother_l_name)
                    .addParameter("image", getStringImage(DataClass.user_image))
                    .addParameter("mobileno", DataClass.user_mobile)
                    .addParameter("whatsno", DataClass.user_whatsapp)
                    .addParameter("alternatno", DataClass.user_alternate)
                    .addParameter("email", DataClass.user_email)
                    .addParameter("fatherno", DataClass.father_mobile)
                    .addParameter("motherno", DataClass.mother_mobile)
                    .addParameter("qualifi", DataClass.user_qualification)
                    .addParameter("university", DataClass.user_school)
                    .addParameter("passing", DataClass.user_passing)
                    .addParameter("designation", DataClass.designation)
                    .addParameter("company_name", DataClass.company_name)
                    .addParameter("website", DataClass.website)
                    .addParameter("college", DataClass.college_name)
                    .addParameter("college_add", DataClass.college_address)
                    .addParameter("findus", DataClass.source)
                    .addParameter("course", DataClass.courses.toString())
                    .addParameter("regisdate", strDate)
                    .addParameter("image1", images.get(0))
                    .addParameter("image2", images.get(1))
                    .addParameter("image3", images.get(2))
                    .addParameter("image4", images.get(3))
                    .addParameter("image5", images.get(4))
                    .addParameter("image6", images.get(5))
                    .addParameter("image7", images.get(6))
                    .addParameter("image8", images.get(7))
                    .addParameter("image9", images.get(8))
                    .addParameter("image10", images.get(9))
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .setDelegate(new UploadStatusDelegate() {
                        @Override
                        public void onProgress(Context context, UploadInfo uploadInfo) {

                        }

                        @Override
                        public void onError(Context context, UploadInfo uploadInfo, ServerResponse serverResponse, Exception exception) {
                            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }

                        @Override
                        public void onCompleted(Context context, UploadInfo uploadInfo, ServerResponse serverResponse) {
                            submit.setVisibility(View.GONE);
                            home.setVisibility(View.VISIBLE);
                            Toast.makeText(context, "Uploaded\n\n"+serverResponse.getBodyAsString(), Toast.LENGTH_SHORT).show();
                            pd.dismiss();
//                            thanku(view);
                        }

                        @Override
                        public void onCancelled(Context context, UploadInfo uploadInfo) {
                            pd.dismiss();
                        }
                    }).startUpload();

        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ButterKnife.bind(this);

        total_fees.setText(DataClass.total_fees);
        reg_fees.setText(DataClass.reg_fees);

        images = new ArrayList<>();
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");
        images.add("");

        for (int i = 0; i < DataClass.docs.size(); i++) {
            images.set(i, getStringImage(DataClass.docs.get(i)));
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admission();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClass.clearAllData();
                Intent intent = new Intent(AdminActivity.this, WelcomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //this will always start your activity as a new task
                startActivity(intent);
            }
        });
    }

    public void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        strDate = mdformat.format(calendar.getTime());
    }

    public String getStringImage(Bitmap bmp) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 40, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        } catch (Exception e) {
            return null;
        }
    }
}

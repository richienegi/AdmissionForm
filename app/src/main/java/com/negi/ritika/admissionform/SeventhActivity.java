package com.negi.ritika.admissionform;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.negi.ritika.admissionform.Fragments.Accounts;
import com.negi.ritika.admissionform.Fragments.ITCourse;
import com.negi.ritika.admissionform.Fragments.Marketing;
import com.negi.ritika.admissionform.Fragments.SoftSkills;
import com.negi.ritika.admissionform.Model_Class.DataClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeventhActivity extends AppCompatActivity {

    Button mnext, mback;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    SectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);
        ButterKnife.bind(this);

        mnext = (Button) findViewById(R.id.next);
        mback = (Button) findViewById(R.id.back);

        adapter = new SectionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataClass.courses.size()>0) {
                    Intent i = new Intent(SeventhActivity.this, EighthActivity.class);
                    startActivity(i);
                }
                else
                {
                    LayoutInflater li = getLayoutInflater();
                    //Getting the View object as defined in the customtoast.xml file
                    View layout = li.inflate(R.layout.customtoast, (ViewGroup) findViewById(R.id.custom_toast_layout));
                    TextView tv = (TextView) layout.findViewById(R.id.custom_toast_message);
                    tv.setText("Please Select the Course you Want to Persue");
                    Toast toast = new Toast(SeventhActivity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();
                }
            }
        });
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClass.courses.clear();
                finish();
            }
        });
    }

    class SectionAdapter extends FragmentPagerAdapter {
        public SectionAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Accounts();
                case 1:
                    return new SoftSkills();
                case 2:
                    return new Marketing();
                case 3:
                    return new ITCourse();
                default:
                    return new Accounts();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}

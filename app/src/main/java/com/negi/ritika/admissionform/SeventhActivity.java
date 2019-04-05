package com.negi.ritika.admissionform;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.negi.ritika.admissionform.Fragments.Accounts;
import com.negi.ritika.admissionform.Fragments.ITCourse;
import com.negi.ritika.admissionform.Fragments.Marketing;
import com.negi.ritika.admissionform.Fragments.SoftSkills;

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
                Intent i = new Intent(SeventhActivity.this, EighthActivity.class);
                startActivity(i);
            }
        });
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

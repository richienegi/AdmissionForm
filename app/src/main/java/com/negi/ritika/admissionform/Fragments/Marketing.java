package com.negi.ritika.admissionform.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.negi.ritika.admissionform.Model_Class.DataClass;
import com.negi.ritika.admissionform.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Marketing extends Fragment {
    String[] Marketing_Courses = {"Digital Marketing", "SEO", "PPC", "Adsense", "Other"};

    ListView ls;

    public Marketing() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_marketing, container, false);
        ls = (ListView)v.findViewById(R.id.list);
        ls = (ListView)v.findViewById(R.id.list);
        ls.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter arr = new ArrayAdapter(getContext(), R.layout.listlayout, Marketing_Courses);
        ls.setAdapter(arr);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (DataClass.courses.contains(selectedItem)) {
                    DataClass.courses.remove(selectedItem);
                } else {
                    DataClass.courses.add(selectedItem);
                }
            }
        });
        return v;
    }

}

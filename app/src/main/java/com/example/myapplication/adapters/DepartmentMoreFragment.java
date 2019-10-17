package com.example.myapplication.adapters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.tsec_application.Home.HomeFragment;
//import com.example.tsec_application.Home.Model.MoreFacilitiesModel;
//import com.example.tsec_application.Home.adapters.MoreFacilitiesAdapter;
//import com.example.tsec_application.R;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DepartmentMoreFragment extends Fragment {

    View view;
    Bundle currentBundle;
    RecyclerView recyclerView;
    //MoreFacilitiesAdapter adapter;
    List<Object> facilityList;
    String key;
    String currentTime;
    String month;
    List<String> moreInfo;
    Button btn;
    DatabaseReference dbMore;
    //DatabaseReference dbMore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_department_more, container, false);
        currentBundle = getArguments();
        Object[] keyObjs = currentBundle.keySet().toArray();
        key = currentBundle.getString((String)keyObjs[0]);
        //((HomeFragment)getActivity()).changeColor(key);
        return view;
    }

    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        currentTime = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
        month = currentTime.substring(5);
        TextView se =  view.findViewById(R.id.more_tv_se);
        TextView te =  view.findViewById(R.id.more_tv_te);
        TextView be =  view.findViewById(R.id.more_tv_be);
        if(month.compareTo("06")<=0) {
            se.setText("SE SEM IV");
            te.setText("TE SEM VI");
            be.setText("BE SEM VIII");
        }

        dbMore = FirebaseDatabase.getInstance().getReference("home/more").child(key);
        dbMore.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d("TAG",dataSnapshot+"");
                    facilityList = new ArrayList<>();
                    moreInfo = new ArrayList<String>();
                    if(dataSnapshot.hasChildren()) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            switch (dataSnapshot1.getKey()) {
                                case "facilities":
                                    if (dataSnapshot1.hasChildren()) {
                                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                            //MoreFacilitiesModel moreFacilities = dataSnapshot2.getValue(MoreFacilitiesModel.class);
                                            //facilityList.add(moreFacilities);
                                        }
                                    }
                                    recyclerView = view.findViewById(R.id.more_facilities_fragment_recycler_view);
                                    //recyclerView.setNestedScrollingEnabled(false);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    //adapter = new MoreFacilitiesAdapter(facilityList);
                                    //recyclerView.setAdapter(adapter);
                                    break;
                                case "sem3":
                                    if (month.compareTo("06") <= 0)
                                        break;
                                    if (dataSnapshot1.hasChildren())
                                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                            moreInfo.add(dataSnapshot2.getValue().toString());
                                        }
                                    break;
                                case "sem4":
                                    if (month.compareTo("06") > 0)
                                        break;
                                    if (dataSnapshot1.hasChildren())
                                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                            moreInfo.add(dataSnapshot2.getValue().toString());
                                        }
                                    break;
                                case "sem5":
                                    if (month.compareTo("06") <= 0)
                                        break;
                                    if (dataSnapshot1.hasChildren())
                                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                            moreInfo.add(dataSnapshot2.getValue().toString());
                                        }
                                    break;
                                case "sem6":
                                    if (month.compareTo("06") > 0)
                                        break;
                                    if (dataSnapshot1.hasChildren())
                                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                            moreInfo.add(dataSnapshot2.getValue().toString());
                                        }
                                    break;
                                case "sem7":
                                    if (month.compareTo("06") <= 0)
                                        break;
                                    if (dataSnapshot1.hasChildren())
                                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                            moreInfo.add(dataSnapshot2.getValue().toString());
                                        }
                                    break;
                                case "sem8":
                                    if (month.compareTo("06") > 0)
                                        break;
                                    if (dataSnapshot1.hasChildren())
                                        for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                                            moreInfo.add(dataSnapshot2.getValue().toString());
                                        }
                                    break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

//        btn = view.findViewById(R.id.more_btn_se_tt);
//        btn.setBackground(getResources().getDrawable(R.drawable.bg_know_more));
//        changeColor(btn, key);
//        btn.setOnClickListener(this);
//        btn = view.findViewById(R.id.more_btn_te_tt);
//        btn.setBackground(getResources().getDrawable(R.drawable.bg_know_more));
//        changeColor(btn, key);
//        btn.setOnClickListener(this);
//        btn = view.findViewById(R.id.more_btn_be_tt);
//        btn.setBackground(getResources().getDrawable(R.drawable.bg_know_more));
//        changeColor(btn, key);
//        btn.setOnClickListener(this);
//        btn = view.findViewById(R.id.more_btn_se_ac);
//        btn.setBackground(getResources().getDrawable(R.drawable.bg_know_more));
//        changeColor(btn, key);
//        btn.setOnClickListener(this);
//        btn = view.findViewById(R.id.more_btn_te_ac);
//        btn.setBackground(getResources().getDrawable(R.drawable.bg_know_more));
//        changeColor(btn, key);
//        btn.setOnClickListener(this);
//        btn = view.findViewById(R.id.more_btn_be_ac);
//        btn.setBackground(getResources().getDrawable(R.drawable.bg_know_more));
//        changeColor(btn, key);
//        btn.setOnClickListener(this);



    }
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.more_btn_se_tt:
                if(key=="comps")
                    openDialog("C1", "C2", "SE", moreInfo.get(1), moreInfo.get(2));
                else if(key=="it")
                    openDialog("S1", "S2", "SE", moreInfo.get(1), moreInfo.get(2));
                else
                    Toast.makeText(v.getContext(), moreInfo.get(1)+"Time Table Coming Soon for SE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_btn_te_tt:
                if(key=="comps")
                    openDialog("C1", "C2", "TE", moreInfo.get(4), moreInfo.get(5));
                else if(key=="it")
                    openDialog("T1", "T2", "TE", moreInfo.get(4), moreInfo.get(5));
                else
                    Toast.makeText(v.getContext(), moreInfo.get(4)+"Time Table Coming Soon for TE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_btn_be_tt:
                if(key=="comps")
                    openDialog("C1", "C2", "BE", moreInfo.get(7), moreInfo.get(8));
                else if(key=="it")
                    openDialog("B1", "B2", "BE", moreInfo.get(7), moreInfo.get(8));
                else
                    Toast.makeText(v.getContext(), moreInfo.get(7)+"Time Table Coming Soon for BE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_btn_se_ac:
                Toast.makeText(v.getContext(), moreInfo.get(0)+"Academic Calendar Coming Soon for SE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_btn_te_ac:
                Toast.makeText(v.getContext(), moreInfo.get(3)+"Academic Calendar Coming Soon for TE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_btn_be_ac:
                Toast.makeText(v.getContext(), moreInfo.get(6)+"Academic Calendar Coming Soon for BE", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void openDialog(String batch1, String batch2, String year, String tt1, String tt2) {
        //Opens a dialog in case of comps or it
        Bundle args = new Bundle();
        args.putString("Batch1", batch1);
        args.putString("Batch2", batch2);
        args.putString("Year", year);
        args.putString("tt1", tt1);
        args.putString("tt2", tt2);
        MoreDownloadDialog moreDownloadDialog = new MoreDownloadDialog();
        moreDownloadDialog.setArguments(args);
        moreDownloadDialog.show(getFragmentManager(), "More Download Dialog");
    }

    void changeColor(View view, String key){
        //Changes colors of buttons
        switch(key){
            case "comps":
                view.setBackgroundTintList(getResources().getColorStateList(R.color.comps));
                break;
            case "it":
                view.setBackgroundTintList(getResources().getColorStateList(R.color.it));
                break;
            case "chem":
                view.setBackgroundTintList(getResources().getColorStateList(R.color.chem));
                break;
            case "extc":
                view.setBackgroundTintList(getResources().getColorStateList(R.color.extc));
                break;
            case "biomed":
                view.setBackgroundTintList(getResources().getColorStateList(R.color.biomed));
                break;
            case "biotech":
                view.setBackgroundTintList(getResources().getColorStateList(R.color.biotech));
                break;
            default:
                view.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
                break;
        }
    }
    */
}

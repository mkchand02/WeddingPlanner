package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.fragment.AllFiveFragment;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnEngage, btnMehendi, btnSangeet, btnReception, btnWedding;
    private FirebaseAuth firebaseAuth;
    Bundle args;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Welcome!");
        btnEngage = getActivity().findViewById(R.id.btn_engagement);
        btnMehendi = getActivity().findViewById(R.id.btn_mehendi);
        btnSangeet = getActivity().findViewById(R.id.btn_sangeet);
        btnReception = getActivity().findViewById(R.id.btn_reception);
        btnWedding = getActivity().findViewById(R.id.btn_wedding);
        firebaseAuth = FirebaseAuth.getInstance();
        btnEngage.setOnClickListener(this);
        btnMehendi.setOnClickListener(this);
        btnSangeet.setOnClickListener(this);
        btnReception.setOnClickListener(this);
        btnWedding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AllFiveFragment allFiveFragment = new AllFiveFragment();
        switch (view.getId()) {
            case R.id.btn_engagement:
                args.putString("event","engagement");
                allFiveFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
            case R.id.nav_mehendi:
                args.putString("event","mehendi");
                allFiveFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
            case R.id.nav_sangeet:
                args.putString("event","sangeet");
                allFiveFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
            case R.id.nav_wedding:
                args.putString("event","wedding");
                allFiveFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
            case R.id.nav_reception:
                args.putString("event","reception");
                allFiveFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,allFiveFragment).commit();
                break;
        }

    }
}

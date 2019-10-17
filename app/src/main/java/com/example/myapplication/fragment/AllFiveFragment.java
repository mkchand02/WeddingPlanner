package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class AllFiveFragment extends Fragment implements View.OnClickListener{


    Bundle args;
    Bundle bundle;
    String event;
    TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        args = getArguments();
        event = args.getString("event");
        return inflater.inflate(R.layout.fragment_all_five,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        title = getActivity().findViewById(R.id.tv_frag);
        title.setText(event.toUpperCase());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cat:
                args.putString("event2","catering");
                AllFiveFragment fragmentCatering = new AllFiveFragment();
                fragmentCatering.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentCatering).commit();
                break;
            case R.id.tv_venue:
                args.putString("event2","venue");
                AllFiveFragment fragmentVenue = new AllFiveFragment();
                fragmentVenue.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentVenue).commit();
                break;
            case R.id.tv_decor:
                args.putString("event2","decoration");
                AllFiveFragment fragmentDecoration = new AllFiveFragment();
                fragmentDecoration.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentDecoration).commit();
                break;
            case R.id.tv_clo:
                args.putString("event2","clothing");
                AllFiveFragment fragmentClothing = new AllFiveFragment();
                fragmentClothing.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentClothing).commit();
                break;
        }
    }
}

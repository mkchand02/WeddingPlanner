package com.example.myapplication.adapters;
/*
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tsec_application.Home.Model.MoreFacilitiesModel;
import com.example.tsec_application.R;

import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class MoreFacilitiesAdapter extends RecyclerView.Adapter<MoreFacilitiesAdapter.ViewHolder> {

    List<MoreFacilitiesModel> facilitylList;

    public MoreFacilitiesAdapter(List<MoreFacilitiesModel> facilitylList) {
        this.facilitylList = facilitylList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.department_more_facilities_model_design, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        MoreFacilitiesModel facility = facilitylList.get(position);
        viewHolder.facility.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        viewHolder.facility.setText(facility.getFacility());
    }

    @Override
    public int getItemCount() {
        return facilitylList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView facility;
        ImageView imageBulletFac;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            facility = itemView.findViewById(R.id.tv_fac);
            imageBulletFac = itemView.findViewById(R.id.bullet_fac);
        }
    }
}
*/
package com.example.test.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.model.Facility;
import com.example.test.model.Option;
import com.example.test.viewholders.FacilityViewHolder;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<FacilityViewHolder> {
    private List<Facility> facilities;
    private Context context;

    public RecyclerAdapter(List<Facility> facilities) {
        this.facilities = facilities;
    }

    @NonNull
    @Override
    public FacilityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        return new FacilityViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityViewHolder facilityViewHolder, int i) {
            facilityViewHolder.tv_facility.setText(facilities.get(i).getName());
            if(facilities.get(i).getOptions().size()>0){
                for(Option option : facilities.get(i).getOptions()){
                    TextView optionsText = new TextView(context);
                    optionsText.setText(option.getName());
                    facilityViewHolder.row_item_layout.addView(optionsText);
                }
                View view = new View(context);
                view.setMinimumHeight(1);
                view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }
    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }
}

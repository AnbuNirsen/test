package com.example.test.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.R;

public class FacilityViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout row_item_layout;
    public TextView tv_facility;
    public FacilityViewHolder(@NonNull View itemView) {
        super(itemView);
        row_item_layout = itemView.findViewById(R.id.row_item_layout);
        tv_facility = itemView.findViewById(R.id.tv_facility);

    }
}

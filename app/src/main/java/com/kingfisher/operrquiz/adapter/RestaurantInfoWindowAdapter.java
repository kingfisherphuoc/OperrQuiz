package com.kingfisher.operrquiz.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.kingfisher.operrquiz.R;
import com.kingfisher.operrquiz.model.data.Business;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by kingfisher on 6/17/17.
 */

public class RestaurantInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    @BindView (R.id.tvName)
    TextView tvName;
    @BindView (R.id.ratingBar)
    MaterialRatingBar ratingBar;
    @BindView (R.id.tvAddress)
    TextView tvAddress;
    View view;

    public RestaurantInfoWindowAdapter(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.infowindow_restaurant, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.width = context.getResources().getDimensionPixelSize(R.dimen.margin_300);
        view.setLayoutParams(layoutParams);
        ButterKnife.bind(this, view);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        Business business = (Business) marker.getTag();
        tvName.setText(business.name);
        ratingBar.setRating(business.rating);

        if (business.location != null && business.location.displayAddress != null && business.location.displayAddress != null) {
            String address = TextUtils.join(" ", business.location.displayAddress);
            tvAddress.setText(address);
        } else {
            tvAddress.setText(R.string.label_unknown);
        }

        return view;
    }
}

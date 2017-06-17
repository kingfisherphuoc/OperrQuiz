package com.kingfisher.operrquiz.view;

import android.location.Location;

import com.kingfisher.operrquiz.model.data.Business;

import java.util.List;

/**
 * Created by kingfisher on 6/17/17.
 */

public interface RestaurantMapView {
    void showMapAtPosition(Location location);

    void showBusinessList(List<Business> businesses);

    void showLoadingFailed();
}

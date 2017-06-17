package com.kingfisher.operrquiz.fragment;

import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kingfisher.operrquiz.R;
import com.kingfisher.operrquiz.adapter.RestaurantInfoWindowAdapter;
import com.kingfisher.operrquiz.model.data.Business;
import com.kingfisher.operrquiz.presenter.RestaurantMapPresenter;
import com.kingfisher.operrquiz.view.RestaurantMapView;
import com.kingfisher.operrquiz.wizard.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by kingfisher on 6/17/17.
 */

public class RestaurantMapFragment extends BaseFragment implements OnMapReadyCallback, RestaurantMapView, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnInfoWindowClickListener {
    private static final LatLng NEW_YORK_LATLNG = new LatLng(40.72326094793552, -73.94870605319738);
    private RestaurantMapPresenter restaurantMapPresenter;
    private GoogleMap googleMap;
    private List<Marker> markers = new ArrayList<>();
    private BitmapDescriptor iconRestaurant;

    private boolean shouldLoadRestaurant = true;
    private boolean isFirstLoad = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_restaurant_map;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        restaurantMapPresenter = new RestaurantMapPresenter(this);
        restaurantMapPresenter.loadTokenIfNeed();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // map ready
        this.googleMap = googleMap;
        setupGoogleMap();
//        restaurantMapPresenter.loadCurrentLocation(getContext());
    }

    private void setupGoogleMap() {
        if (PermissionChecker.isPermissionGranted(getContext())) {
            this.googleMap.setMyLocationEnabled(true);
        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(NEW_YORK_LATLNG)
                .zoom(15).build();
        this.googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        this.googleMap.setOnCameraIdleListener(this);
        this.googleMap.setOnCameraMoveStartedListener(this);
        this.googleMap.setInfoWindowAdapter(new RestaurantInfoWindowAdapter(getContext()));
        this.googleMap.setOnInfoWindowClickListener(this);

        iconRestaurant = BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant);

    }

    @Override
    public void showMapAtPosition(Location location) {
        if (googleMap == null) return;
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));

    }

    @Override
    public void showBusinessList(List<Business> businesses) {
        Timber.e("Business size: " + businesses.size());
        if (googleMap == null) return;
        clearAddedMarkers();
        for (Business business : businesses) {
            addMarker(business);
        }

    }

    /**
     * Clear all the markers added previously
     */
    private void clearAddedMarkers() {
        for (Marker marker : markers) {
            marker.remove();
        }
    }


    /**
     * Add business marker to the map
     *
     * @param business
     */
    private void addMarker(Business business) {
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .icon(iconRestaurant)
                .position(new LatLng(business.coordinate.latitude, business.coordinate.longitude))
                .title(business.name));
        marker.setTag(business);
        markers.add(marker);
    }

    @Override
    public void showLoadingFailed() {

    }

    @Override
    public void onCameraMove() {
//        Timber.i("Camera Move");
    }

    @Override
    public void onCameraIdle() {
        Timber.e("Camera stop moving: " + googleMap.getCameraPosition().target.toString());
        if (isFirstLoad || shouldLoadRestaurant) {
            restaurantMapPresenter.loadRestaurantsAround(googleMap.getCameraPosition().target);
            isFirstLoad = false;
        }
    }

    @Override
    public void onCameraMoveStarted(int reason) {
        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) { // only load if user moves map
            shouldLoadRestaurant = true;
        } else {
            shouldLoadRestaurant = false;
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Business business = (Business) marker.getTag();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.frameContainer, RestaurantDetailFragment.newInstance(business))
                .addToBackStack(null)
                .commit();
    }
}

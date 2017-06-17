package com.kingfisher.operrquiz.presenter;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kingfisher.operrquiz.model.data.BusinessResponse;
import com.kingfisher.operrquiz.model.data.LoginResponse;
import com.kingfisher.operrquiz.model.sao.ClientConstants;
import com.kingfisher.operrquiz.model.sao.ServiceGenerator;
import com.kingfisher.operrquiz.view.RestaurantMapView;
import com.kingfisher.operrquiz.wizard.PermissionChecker;
import com.kingfisher.operrquiz.wizard.TokenManager;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by kingfisher on 6/17/17.
 */

public class RestaurantMapPresenter {

    private WeakReference<RestaurantMapView> restaurantMapViewWeakReference;
    private Call<BusinessResponse> businessResponseCall;

    public RestaurantMapPresenter(RestaurantMapView restaurantMapView) {
        this.restaurantMapViewWeakReference = new WeakReference<RestaurantMapView>(restaurantMapView);
    }

    private RestaurantMapView getRestaurantMapView() {
        return restaurantMapViewWeakReference != null ? restaurantMapViewWeakReference.get() : null;
    }


    /**
     * Load last known position from the device and show camera at that pos.
     *
     * @param context
     */
    public void loadCurrentLocation(Context context) {
        if (!PermissionChecker.isPermissionGranted(context)) {
            return;
        }
        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (getRestaurantMapView() != null) {
                    getRestaurantMapView().showMapAtPosition(location);
                }
            }
        });
    }

    /**
     * Load access token to pass to Yelp API
     */
    public void loadTokenIfNeed() {
        // only load a new token if the old token is invalid
        if (TokenManager.isTokenValid()) {
            return;
        }
        ServiceGenerator.getYelpApi().getAccessToken(ClientConstants.GRANT_TYPE_OAUTH2,
                ClientConstants.CLIENT_ID,
                ClientConstants.CLIENT_SECRETE)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response != null && response.body() != null) {
                            TokenManager.setToken(response.body().accessToken);
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Timber.e(t);
                    }
                });
    }

    /**
     * Load restaurants arount a position
     *
     * @param position
     */
    public void loadRestaurantsAround(LatLng position) {
        if (!TokenManager.isTokenValid()) {
            Timber.e("Token is invalid");
            return;
        }
        // cancel previous call to make new call
        if (businessResponseCall != null) {
            businessResponseCall.cancel();
        }
        businessResponseCall = ServiceGenerator.getYelpApi().getBusiness(TokenManager.getBearToken(),
                position.latitude, position.longitude, ClientConstants.RESTAURANT_KEY);
        businessResponseCall.enqueue(new Callback<BusinessResponse>() {
            @Override
            public void onResponse(Call<BusinessResponse> call, Response<BusinessResponse> response) {
                businessResponseCall = null;
                if (getRestaurantMapView() == null) return;
                if (response == null || response.body() == null) {
                    getRestaurantMapView().showLoadingFailed();
                    return;
                } else {
                    getRestaurantMapView().showBusinessList(response.body().businesses);
                }

            }

            @Override
            public void onFailure(Call<BusinessResponse> call, Throwable t) {
                businessResponseCall = null;
                Timber.e(t);
            }
        });
    }


}

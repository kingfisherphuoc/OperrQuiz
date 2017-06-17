package com.kingfisher.operrquiz.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kingfisher.operrquiz.R;
import com.kingfisher.operrquiz.model.data.Business;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by kingfisher on 6/17/17.
 */

public class RestaurantDetailFragment extends BaseFragment {


    @BindView (R.id.toolbar)
    Toolbar toolbar;
    @BindView (R.id.ivImage)
    ImageView ivImage;
    @BindView (R.id.tvName)
    TextView tvName;
    @BindView (R.id.ratingBar)
    MaterialRatingBar ratingBar;
    @BindView (R.id.tvNumber)
    TextView tvNumber;
    @BindView (R.id.tvCategories)
    TextView tvCategories;
    @BindView (R.id.tvAddress)
    TextView tvAddress;
    private Business business;


    public static RestaurantDetailFragment newInstance(Business business) {
        RestaurantDetailFragment restaurantDetailFragment = new RestaurantDetailFragment();
        restaurantDetailFragment.business = business;
        return restaurantDetailFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_restaurant_detail;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {
        setupToolBar();
        displayDetailInfo();
    }

    private void setupToolBar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void displayDetailInfo() {
        if (business == null) return;
        toolbar.setTitle(business.name);
        tvName.setText(business.name);
        tvNumber.setText(getString(R.string.label_phone) + business.phone);
        ratingBar.setRating(business.rating);

        if (business.categories != null) {
            List<String> cats = new ArrayList<>();
            for (Business.Category category : business.categories) {
                cats.add(category.title);
            }
            tvCategories.setText(getString(R.string.label_cate) + TextUtils.join(", ", cats));
        } else {
            tvCategories.setText(getString(R.string.label_cate) + R.string.label_unknown);
        }

        if (business.location != null && business.location.displayAddress != null && business.location.displayAddress != null) {
            String address = TextUtils.join(" ", business.location.displayAddress);
            tvAddress.setText(getString(R.string.label_address) + address);
        } else {
            tvAddress.setText(getString(R.string.label_address) + R.string.label_unknown);
        }

        Glide.with(this).load(business.imageUrl).into(ivImage);

    }


}

package com.kingfisher.operrquiz.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {


    /**
     * @return resource id of layout of fragment
     */
    abstract protected int getLayoutId();

    protected abstract void initializeViews(View view, Bundle savedInstanceState);

    protected void setContentForView(View view) {
    }

    /**
     * Function only called when fragment is created when configuration changed
     *
     * @param savedInstanceState
     */
    protected void onOrientationChanged(Bundle savedInstanceState) {
        // Call when orientation changed
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            onOrientationChanged(savedInstanceState);
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initializeViews(view, savedInstanceState);
        setContentForView(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}

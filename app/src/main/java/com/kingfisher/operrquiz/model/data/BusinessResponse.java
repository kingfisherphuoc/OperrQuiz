package com.kingfisher.operrquiz.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kingfisher on 6/17/17.
 */

public class BusinessResponse {
    @SerializedName ("businesses")
    public List<Business> businesses;
}

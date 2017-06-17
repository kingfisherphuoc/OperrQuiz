package com.kingfisher.operrquiz.model.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kingfisher on 6/17/17.
 */

public class LoginResponse {
    /**
     * access_token : 1Qmq5QK769nyO6_ukfKFGrXKKAkHRXC31X9IRdKGUWzfjQnSlNAkbsv9ca-FqacNHR3-mHmSqyiC1Zm67az8BkVthTgz39ZXSJqwnT9rWylLPTw07Tyvh9yM_-sAWXYx
     * expires_in : 11111165
     * token_type : Bearer
     */
    @SerializedName ("access_token")
    public String accessToken;
    @SerializedName ("expires_in")
    public int expiresIn;
    @SerializedName ("token_type")
    public String tokenType;


    @Override
    public String toString() {
        return "LoginResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}

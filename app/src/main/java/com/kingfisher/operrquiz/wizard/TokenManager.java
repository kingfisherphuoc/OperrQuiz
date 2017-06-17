package com.kingfisher.operrquiz.wizard;

import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import com.kingfisher.operrquiz.model.sao.ClientConstants;

/**
 * Created by kingfisher on 6/17/17.
 */

public class TokenManager {
    private static final String KEY_TOKEN = "token";
    private static final String KEY_TIME = "time";
    private static final long ONE_EIGHTY_DAY = 15552000000l; // 180days in miliseconds

    /**
     * Set token and valid time
     *
     * @param token
     */
    public static void setToken(String token) {
        SharedPreferencesManager.getInstance().putValue(KEY_TOKEN, token);
        SharedPreferencesManager.getInstance().putValue(KEY_TIME, System.currentTimeMillis());
    }

    /**
     * Get bear token to use with header
     *
     * @return
     */
    public static String getBearToken() {
        return isTokenValid() ?
                ClientConstants.TOKEN_TYPE + SharedPreferencesManager.getInstance().getValue(KEY_TOKEN, String.class) : null;
    }

    /**
     * Is the token valid until today?
     *
     * @return
     */
    public static boolean isTokenValid() {
        long time = SharedPreferencesManager.getInstance().getValue(KEY_TIME, Long.class);
        return (System.currentTimeMillis() - time) < ONE_EIGHTY_DAY;
    }
}

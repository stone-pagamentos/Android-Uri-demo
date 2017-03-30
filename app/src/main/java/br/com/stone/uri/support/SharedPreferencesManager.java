package br.com.stone.uri.support;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author filpgame
 * @since 2017-03-30
 */

public class SharedPreferencesManager {
    private static final String SHARED_NAME = "stone_demo";
    private Context context;

    private SharedPreferencesManager(Context context) {
        this.context = context;
    }

    public static SharedPreferencesManager newInstance(Context context) {
        return new SharedPreferencesManager(context);
    }

    public String getStoneCode() {
        return getSharedPreferences().getString(Keys.STONE_CODE, "");
    }

    public void setStoneCode(String stoneCode) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(Keys.STONE_CODE, stoneCode);
        editor.apply();
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    private class Keys {
        static final String STONE_CODE = "stone_code";
    }
}

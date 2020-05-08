package com.asura.blockededittext;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Methods {

    public static boolean isActivityAlive(Activity activity) {
        return activity != null && !activity.isFinishing();
    }   

    public static void hideKeyboard(Activity activity) {
        if (!isActivityAlive(activity)) return;
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

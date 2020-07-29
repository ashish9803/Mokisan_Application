package com.farmhike.mokisan.Models;

import android.content.Context;

public class AppContext {
    private static AppContext obj;
    private Context context;

    private AppContext() {
    }

    public static AppContext getInstance() {
        if (obj == null) {
            obj = new AppContext();
            return obj;
        } else
            return obj;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}

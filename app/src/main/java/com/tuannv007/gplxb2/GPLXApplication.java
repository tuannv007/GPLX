package com.tuannv007.gplxb2;

import android.app.Application;
import android.content.Context;

import com.tuannv007.gplxb2.database.DatabaseAccess;

public class GPLXApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}

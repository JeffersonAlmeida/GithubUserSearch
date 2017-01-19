package com.gus.githubusersearch;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.gus.githubusersearch.injection.DaggerGusComponents;
import com.gus.githubusersearch.injection.GusComponents;
import com.gus.githubusersearch.injection.application.ApplicationComponent;
import com.gus.githubusersearch.injection.application.ApplicationModule;
import com.gus.githubusersearch.injection.application.DaggerApplicationComponent;

public class GusApplication extends MultiDexApplication {

    private static final String TAG = GusApplication.class.getSimpleName();

    private static GusApplication gusApplication;

    private static GusComponents gusComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        gusApplication = GusApplication.this;
        initDependencyInjection();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized GusApplication get(){
        if ( gusApplication == null ){
            throw new IllegalStateException(TAG + " Is not initialized!");
        }
        return gusApplication;
    }

    private void initDependencyInjection() {

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();

        gusComponents = DaggerGusComponents.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

    public static synchronized GusComponents getGusComponents(){
        return gusComponents;
    }

}

package com.gus.githubusersearch;

import android.app.Application;

import com.gus.githubusersearch.injection.GusComponents;
import com.gus.githubusersearch.injection.application.ApplicationComponent;
import com.gus.githubusersearch.injection.application.ApplicationModule;
import com.gus.githubusersearch.injection.application.DaggerGusComponents;
import com.gus.githubusersearch.injection.application.DaggerApplicationComponent;

public class GusApplication extends Application {

    private static final String TAG = GusApplication.class.getSimpleName();

    private static GusApplication gusApplication;

    private static GusComponents gusComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        gusApplication = GusApplication.this;
        initDependencyInjection();
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

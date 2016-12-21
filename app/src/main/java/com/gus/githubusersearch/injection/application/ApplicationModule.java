package com.gus.githubusersearch.injection.application;

import com.gus.githubusersearch.GusApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    GusApplication provideApplication(){
        return GusApplication.get();
    }

}

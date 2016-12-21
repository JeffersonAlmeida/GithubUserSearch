package com.gus.githubusersearch.injection.application;

import com.gus.githubusersearch.GusApplication;

import dagger.Component;

@Component ( modules = ApplicationModule.class )
public interface ApplicationComponent {

    GusApplication provideGusApplication();

}


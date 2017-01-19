package com.gus.githubusersearch.injection;

import com.gus.githubusersearch.MainActivity;
import com.gus.githubusersearch.injection.application.ApplicationComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component ( dependencies = ApplicationComponent.class )
public interface GusComponents {

    void inject(MainActivity activity);

}

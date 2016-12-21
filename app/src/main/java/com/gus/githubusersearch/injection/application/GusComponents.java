package com.gus.githubusersearch.injection.application;

import com.gus.githubusersearch.MainActivity;
import com.gus.githubusersearch.remote.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component ( dependencies = ApplicationComponent.class )
public interface GusComponents {

    void inject(MainActivity activity);
    void inject(UserRepositoryImpl userRepository);

}

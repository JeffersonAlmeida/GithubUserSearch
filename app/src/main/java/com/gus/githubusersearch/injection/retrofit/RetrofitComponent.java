package com.gus.githubusersearch.injection.retrofit;

import com.gus.githubusersearch.remote.GithubUserRestService;

import dagger.Component;

@Component( modules = RetrofitModule.class )
public interface RetrofitComponent {

    GithubUserRestService provideGithubUserRestService();
}

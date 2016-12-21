package com.gus.githubusersearch.remote;

import com.gus.githubusersearch.model.User;
import com.gus.githubusersearch.model.UsersList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface GithubUserRestService {

    @GET("/search/users?per_page=2")
    Observable<UsersList> searchGithubUsers(@Query("q") String searchTerm);

    @GET("/users/{username}")
    Observable<User> getUser(@Path("username") String username);
}

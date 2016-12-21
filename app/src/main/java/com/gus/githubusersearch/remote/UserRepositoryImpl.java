package com.gus.githubusersearch.remote;

import com.gus.githubusersearch.GusApplication;
import com.gus.githubusersearch.model.User;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    GithubUserRestService githubUserRestService;

    public UserRepositoryImpl() {
        GusApplication.getGusComponents().inject(this);
    }

    @Override
    public Observable<List<User>> searchUsers(final String searchTerm) {
        return Observable
                .defer(() -> githubUserRestService.searchGithubUsers(searchTerm)
                        .concatMap(usersList -> Observable.from(usersList.getItems())
                                .concatMap(user -> githubUserRestService.getUser(user.getLogin()))
                                .toList()))
                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }

}

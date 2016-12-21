package com.gus.githubusersearch.remote;

import com.gus.githubusersearch.model.User;

import java.util.List;

import rx.Observable;

public interface UserRepository {

    Observable<List<User>> searchUsers(final String searchTerm);

}

package com.gus.githubusersearch.remote;

import com.gus.githubusersearch.model.User;
import com.gus.githubusersearch.model.UsersList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.observers.TestSubscriber;

public class UserRepositoryImplTest {

    private static final String LOGIN = "JeffersonAlmeida";

    @Mock
    GithubUserRestService githubUserRestService;

    UserRepositoryImpl userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userRepository = new UserRepositoryImpl(githubUserRestService);
    }

    @Test
    public void searchUserTest() throws Exception {
        //Given
        Mockito.when(githubUserRestService.getUser(Mockito.anyString()))
                .thenReturn(Observable.just(getUser()));

        // When
        TestSubscriber<User> testSubscriber = new TestSubscriber<>();
        githubUserRestService.getUser(Mockito.anyString()).subscribe(testSubscriber);

        // Then
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();

        List<User> onNextEvents = testSubscriber.getOnNextEvents();
        String login = onNextEvents.get(0).getLogin();
        Assert.assertEquals(login, LOGIN);
    }

    @Test
    public void searchUsersTeminatedWithError() {
        // Given
        Mockito.when(githubUserRestService.searchGithubUsers(Mockito.anyString()))
                .thenReturn(get403ForbidenError());

        // When
        TestSubscriber<UsersList> testSubscriber = new TestSubscriber<>();
        githubUserRestService.searchGithubUsers(LOGIN)
                .subscribe(testSubscriber);

        // Then
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertError(Exception.class);
        testSubscriber.assertNotCompleted();
        testSubscriber.assertNoValues();

        Mockito.verify(githubUserRestService).searchGithubUsers(LOGIN);
        Mockito.verify(githubUserRestService, Mockito.never()).getUser(LOGIN);
    }

    private User getUser() {
        User user = new User();
        user.setLogin(LOGIN);
        return user;
    }

    public Observable<UsersList> get403ForbidenError() {
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("aplication/json"), "Forbidden");
        Response<Object> response = Response.error(403, responseBody);
        HttpException httpException = new HttpException(response);
        return Observable.error(httpException);
    }
}
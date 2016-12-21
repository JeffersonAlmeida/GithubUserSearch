package com.gus.githubusersearch.injection.application.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gus.githubusersearch.remote.GithubUserRestService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    public static final String BASE_URL = "https://api.github.com";

    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLog) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLog);
        return httpClient.build();
    }

    @Provides
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides
    GithubUserRestService provideGithubUserRestService(Retrofit.Builder builder){

        Retrofit retrofit = builder
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(GithubUserRestService.class);
    }

}

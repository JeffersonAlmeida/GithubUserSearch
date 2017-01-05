package com.gus.githubusersearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Inject
    GusApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GusApplication.getGusComponents().inject(this);

        Observable.just(1).subscribe( t -> {
            Log.e("T", "onCreate: " + t);
        });

    }
}

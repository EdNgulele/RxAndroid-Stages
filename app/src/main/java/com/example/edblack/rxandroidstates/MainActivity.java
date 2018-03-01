package com.example.edblack.rxandroidstates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private Observable<Integer> mObservable;
    private Observer<Integer> mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //initialize the Observable
    private void initObservable() {

        mObservable = Observable.create(e -> {
            for (int i = 1; i <= 5; i++) {
                e.onNext(i);
            }
            e.onComplete();
        });
    }

    //initialize Observer
    private void initObserver() {
        mObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Toast.makeText(MainActivity.this, "onSubscribe called", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Integer integer) {
                Toast.makeText(MainActivity.this, "onNext called " + integer, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError called", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "onComplete called", Toast.LENGTH_SHORT).show();
            }
        };
    }

    //On button click
    public void showStates(View view){
        initObservable();
        initObserver();
        mObservable.subscribe(mObserver); //subscribing Observer to Observable
    }

}


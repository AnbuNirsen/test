package com.example.test.presenters;

import android.view.View;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V> {
    protected final V view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public BasePresenter(V view) {
        this.view = view;
    }
    protected void addCompositeDisposable(Disposable d){
        compositeDisposable.add(d);
    }
}

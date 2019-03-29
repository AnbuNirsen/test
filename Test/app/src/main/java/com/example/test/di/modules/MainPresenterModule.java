package com.example.test.di.modules;

import com.example.test.contracts.IMainContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {
    IMainContract.view view;

    public MainPresenterModule(IMainContract.view view) {
        this.view = view;
    }
    @Provides
    IMainContract.view providesView(){
        return view;
    }
}

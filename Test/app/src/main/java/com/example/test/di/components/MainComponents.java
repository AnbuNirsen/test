package com.example.test.di.components;

import com.example.test.di.modules.MainPresenterModule;
import com.example.test.views.MainActivity;

import dagger.Component;

@Component(modules = MainPresenterModule.class)
public interface MainComponents {
    void inject(MainActivity mainActivity);
}

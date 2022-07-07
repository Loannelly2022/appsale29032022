package com.example.appsale29032022.di;

import com.example.appsale29032022.Xehoi;

import dagger.Component;

/**
 * Created by pphat on 7/7/2022.
 */

@Component(modules = XehoiModule.class)
public interface AppComponent {
    Xehoi getXehoi();

    @Component.Builder
    interface Builder {
        AppComponent build();
    }
}

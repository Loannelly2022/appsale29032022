package com.example.appsale29032022.di;

import com.example.appsale29032022.MainActivity;
import com.example.appsale29032022.Xehoi;

import dagger.Component;

/**
 * Created by pphat on 7/7/2022.
 */

@Component(modules = XehoiModule.class)
public interface AppComponent {

    void injectMainActivity(MainActivity mainActivity);

    @Component.Builder
    interface Builder {
        AppComponent build();
    }
}

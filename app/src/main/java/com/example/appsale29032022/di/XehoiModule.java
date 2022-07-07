package com.example.appsale29032022.di;

import com.example.appsale29032022.Banhxe;
import com.example.appsale29032022.Dongco;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pphat on 7/7/2022.
 */

@Module
public class XehoiModule {

    @Provides
    public static Banhxe provideBanhXe() {
        return new Banhxe();
    }

    @Provides
    public static Dongco provideDongco() {
        return new Dongco();
    }
}

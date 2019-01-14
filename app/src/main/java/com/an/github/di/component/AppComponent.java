package com.an.github.di.component;

import android.app.Application;

import com.an.github.AppController;
import com.an.github.di.module.ActivityModule;
import com.an.github.di.module.ApiModule;
import com.an.github.di.module.DbModule;
import com.an.github.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Component(modules = {
                ApiModule.class,
                DbModule.class,
                ViewModelModule.class,
                ActivityModule.class,
                AndroidSupportInjectionModule.class})
@Singleton
public interface AppComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }


    void inject(AppController appController);
}

/*
 * Copyright (C) 2016 Kelvin Leung Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidarchitecture;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.androidarchitecture.di.components.DaggerDiComponent;
import com.androidarchitecture.di.components.DiComponent;
import com.androidarchitecture.di.components.ViewComponent;
import com.androidarchitecture.di.modules.ApplicationModule;
import com.androidarchitecture.di.modules.DatabaseModule;
import com.androidarchitecture.di.modules.NetworkModule;
import com.androidarchitecture.di.modules.ViewModule;

public class ArchitectureApplication extends Application {
    private DiComponent mDiComponent;
    private ViewComponent mViewComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        // Dagger%COMPONENT_NAME%
        try {
            mDiComponent = DaggerDiComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .networkModule(new NetworkModule(BuildConfig.API_URL, BuildConfig.API_VERSION))
                    .databaseModule(new DatabaseModule(!BuildConfig.DEBUG))
                    .build();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public DiComponent getDiComponent() {
        return mDiComponent;
    }

    public ViewComponent createViewComponent() {
        mViewComponent = mDiComponent.plus(new ViewModule());
        return mViewComponent;
    }

    public void releaseViewComponent() {
        mViewComponent = null;
    }
}

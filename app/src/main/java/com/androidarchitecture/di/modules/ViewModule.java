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

package com.androidarchitecture.di.modules;

import com.androidarchitecture.network.api.Api;
import com.androidarchitecture.views.launch.LaunchInteractor;
import com.androidarchitecture.views.launch.LaunchPresenter;
import com.androidarchitecture.views.main.MainInteractor;
import com.androidarchitecture.views.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public final class ViewModule {
    @Provides
    LaunchPresenter provideLaunchPresenter(LaunchInteractor interactor) {
        return new LaunchPresenter(interactor);
    }

    @Provides
    LaunchInteractor provideLaunchInteractor() {
        return new LaunchInteractor();
    }

    @Provides
    MainPresenter provideMainPresenter(MainInteractor interactor) {
        return new MainPresenter(interactor);
    }

    @Provides
    MainInteractor provideMainInteractor(Api api) {
        return new MainInteractor(api);
    }
}

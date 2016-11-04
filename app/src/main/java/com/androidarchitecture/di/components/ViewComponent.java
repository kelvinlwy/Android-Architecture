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

package com.androidarchitecture.di.components;

import com.androidarchitecture.di.modules.ViewModule;
import com.androidarchitecture.di.scopes.ActivityScope;
import com.androidarchitecture.views.launch.LaunchActivity;
import com.androidarchitecture.views.main.MainActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {
        ViewModule.class
})
public interface ViewComponent {
    void inject(LaunchActivity activity);

    void inject(MainActivity activity);
}

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

package com.androidarchitecture.views.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.androidarchitecture.R;
import com.androidarchitecture.di.components.ViewComponent;
import com.androidarchitecture.views.base.BaseActivity;
import com.androidarchitecture.views.main.MainActivity;

public class LaunchActivity extends BaseActivity<LaunchPresenter, LaunchContract.View> implements LaunchContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    @Override
    protected void setupViewComponent(@NonNull ViewComponent viewComponent) {
        viewComponent.inject(this);
    }

    @Override
    protected void setupUI() {

    }

    @Override
    public void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
    }
}

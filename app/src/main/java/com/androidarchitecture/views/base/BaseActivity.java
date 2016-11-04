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

package com.androidarchitecture.views.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.androidarchitecture.ArchitectureApplication;
import com.androidarchitecture.di.components.ViewComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenterImpl<V>, V> extends AppCompatActivity {
    /**
     * The presenter for this view
     */
    @Nullable
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        if (mPresenter != null)
            mPresenter.onRestoreInstance(savedInstanceState);
        if (mPresenter != null)
            mPresenter.onCreate((V) this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setupUI();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        setupUI();
    }

    private void injectDependencies() {
        setupViewComponent(((ArchitectureApplication) getApplication()).createViewComponent());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null)
            mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mPresenter != null)
            mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null)
            mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mPresenter != null)
            mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void finish() {
        super.finish();
        // Release the View sub component
        ((ArchitectureApplication) getApplication()).releaseViewComponent();
    }

    /**
     * Setup the injection component for this view
     *
     * @param viewComponent the view component
     */
    protected abstract void setupViewComponent(@NonNull ViewComponent viewComponent);

    /**
     * Setup the screen and initialize all elements
     */
    protected abstract void setupUI();
}

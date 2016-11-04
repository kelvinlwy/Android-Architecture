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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.androidarchitecture.ArchitectureApplication;
import com.androidarchitecture.di.components.ViewComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenterImpl<V>, V> extends Fragment {
    /**
     * The presenter for this view
     */
    @Nullable
    @Inject
    public P mPresenter;
    /**
     * Unbinder for unbinding views when called
     */
    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        if (mPresenter != null)
            mPresenter.onRestoreInstance(savedInstanceState);
        if (mPresenter != null)
            mPresenter.onCreate((V) this);
    }

    private void injectDependencies() {
        setupComponent(((ArchitectureApplication) getActivity().getApplication()).createViewComponent());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        setupUI();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null)
            mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null)
            mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null)
            mPresenter.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPresenter != null)
            mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPresenter != null)
            mPresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
    }

    /**
     * Setup the injection component for this view
     *
     * @param viewComponent the view component
     */
    public abstract void setupComponent(@NonNull ViewComponent viewComponent);

    /**
     * Setup the screen and initialize all elements
     */
    protected abstract void setupUI();
}

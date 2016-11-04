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
import android.support.annotation.NonNull;

public interface BaseContract {
    interface BaseView {

    }

    interface BasePresenter<V> {
        /**
         * Called when the view is attached to the presenter.
         *
         * @param view the view
         */
        void onCreate(@NonNull V view);

        void onRestoreInstance(Bundle savedInstanceState);

        /**
         * Called every time the view starts, the view is guarantee to be not null starting at this
         * method, until {@link #onStop()} is called.
         */
        void onStart();

        void onResume();

        void onPause();

        void onSaveInstanceState(Bundle outState);

        /**
         * Called every time the view stops. After this method, the view will be null until next
         * {@link #onStart} call.
         */
        void onStop();

        /**
         * Called when the view is detached from the presenter.
         */
        void onDestroy();

        /**
         * Called when the presenter is definitely destroyed, you should use this method only to release
         * any resource used by the presenter (cancel HTTP requests, close database connection...).
         */
        void onPresenterDestroyed();

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    interface BaseInteractor {

    }
}

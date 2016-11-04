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

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.androidarchitecture.ArchitectureApplication;
import com.androidarchitecture.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    private static final int MAX_READ_TIMEOUT_SECONDS = 30;
    private static final int MAX_WRITE_TIMEOUT_SECONDS = 30;
    private static final int MAX_CONNECTION_TIMEOUT_SECONDS = 30;
    private String mBaseUrl;

    public NetworkModule(@NonNull String baseUrl, @NonNull String apiVersion) throws NullPointerException {
        this.mBaseUrl = baseUrl;
        this.mBaseUrl += TextUtils.isEmpty(apiVersion) ? "" : apiVersion + "/";
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(ArchitectureApplication application) {
        int cacheSize = 5 * 1024 * 1024; //5MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor((message) ->
                Timber.tag("Api log").d(message));
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(MAX_CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(MAX_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(MAX_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            // Appling logger system is only for development
            clientBuilder.addNetworkInterceptor(httpLoggingInterceptor);
        }
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(mBaseUrl)
                .build();
    }
}

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

package com.androidarchitecture.views.main;

import com.androidarchitecture.models.Post;
import com.androidarchitecture.network.api.Api;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainInteractor implements MainContract.Interactor {

    private final Api mApi;

    @Inject
    public MainInteractor(Api api) {
        this.mApi = api;
    }

    @Override
    public void getPosts(GetPostsCallback callback) {
        callback.onGetPostsPreExecute();
        mApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGetPostsError();
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        callback.onGetPostsSuccess(posts);
                    }
                });
    }

    @Override
    public void deletePost(Post post, DeletePostCallback callback) {
        callback.onDeletePostPreExecute();
        mApi.deletePost(post.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDeletePostError();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        callback.onDeletePostSuccess();
                    }
                });
    }

    @Override
    public void addPost(Post post, AddPostCallback callback) {
        callback.onAddPostPreExecute();
        mApi.addPost(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onAddPostError();
                    }

                    @Override
                    public void onNext(Post post) {
                        callback.onAddPostSuccess(post);
                    }
                });
    }
}

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

package com.androidarchitecture.network.api;

import android.support.annotation.NonNull;

import com.androidarchitecture.models.Post;
import com.androidarchitecture.network.api.services.ApiService;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;


public class Api extends BaseApi {

    @NonNull
    private final ApiService mApiService;

    @Inject
    public Api(ApiService apiService) {
        this.mApiService = apiService;
    }

    public Observable<List<Post>> getPosts() {
        return mApiService.getPosts();
    }

    public Observable<ResponseBody> getSinglePost(int postId) {
        return mApiService.getSinglePost(postId);
    }

    public Observable<ResponseBody> getCommentsByPostId(int postId) {
        return mApiService.getCommentsByPost(postId);
    }

    public Observable<ResponseBody> getPostsByUserId(int userId) {
        return mApiService.getPostsByUser(userId);
    }

    public Observable<Post> addPost(Post post) {
        return mApiService.addPost(post);
    }

    public Observable<ResponseBody> updatePost(int id, String title, String body, int userId) {
        return mApiService.updatePost(id, title, body, userId);
    }

    public Observable<ResponseBody> updatePostTitle(int id, String title) {
        return mApiService.updatePostTitle(id, title);
    }

    public Observable<ResponseBody> deletePost(int id) {
        return mApiService.deletePost(id);
    }
}

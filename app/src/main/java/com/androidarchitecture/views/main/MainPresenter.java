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

import android.text.TextUtils;

import com.androidarchitecture.models.Post;
import com.androidarchitecture.views.base.BasePresenterImpl;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter, MainContract.Interactor.GetPostsCallback, MainContract.Interactor.DeletePostCallback, MainContract.Interactor.AddPostCallback {

    private final MainInteractor mInteractor;

    @Inject
    public MainPresenter(MainInteractor interactor) {
        this.mInteractor = interactor;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getPostsList();
    }

    @Override
    public void getPostsList() {
        mInteractor.getPosts(this);
    }

    @Override
    public void onGetPostsPreExecute() {
        mView.toggleProgressIndicator(true);
    }

    @Override
    public void onGetPostsSuccess(List<Post> posts) {
        mView.refreshList(posts);
        mView.toggleProgressIndicator(false);
    }

    @Override
    public void onGetPostsError() {
        mView.toggleProgressIndicator(false);
    }

    @Override
    public void removePost(Post post) {
        if (post != null) mInteractor.deletePost(post, this);
    }

    @Override
    public void onDeletePostPreExecute() {

    }

    @Override
    public void onDeletePostSuccess() {

    }

    @Override
    public void onDeletePostError() {

    }

    @Override
    public void addPost(String title, String body) {
        // you can show error message
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(body)) return;
        Post post = new Post();
        post.setUserId(1); // Hardcode user id for show case only
        post.setTitle(title);
        post.setBody(body);
        mInteractor.addPost(post, this);
    }

    @Override
    public void onAddPostPreExecute() {

    }

    @Override
    public void onAddPostSuccess(Post post) {
        mView.prependPostToList(post);
    }

    @Override
    public void onAddPostError() {

    }
}

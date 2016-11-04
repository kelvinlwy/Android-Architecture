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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.androidarchitecture.R;
import com.androidarchitecture.di.components.ViewComponent;
import com.androidarchitecture.models.Post;
import com.androidarchitecture.views.base.BaseActivity;
import com.androidarchitecture.widgets.DividerItemDecoration;
import com.androidarchitecture.widgets.RecyclerViewSwipeToDeleteImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter, MainContract.View> implements MainContract.View, RecyclerViewSwipeToDeleteImpl.SwipeToDeleteListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private PostsRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setupViewComponent(@NonNull ViewComponent viewComponent) {
        viewComponent.inject(this);
    }

    @Override
    protected void setupUI() {
        setSupportActionBar(toolbar);
        // Set list view
        mAdapter = new PostsRecyclerViewAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(mAdapter);
        new RecyclerViewSwipeToDeleteImpl(recyclerView, this);
        // Swipe to refresh
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mPresenter.getPostsList();
    }

    @Override
    public void toggleProgressIndicator(boolean refresh) {
        swipeRefreshLayout.setRefreshing(refresh);
    }

    @Override
    public void refreshList(List<Post> posts) {
        mAdapter.updateItems(posts);
    }

    @Override
    public void removeItem(int position) {
        mAdapter.removeItem(position);
        mPresenter.removePost(mAdapter.getItem(position));
    }

    @OnClick(R.id.fab)
    void onAddClick() {
        mPresenter.addPost("New title", "New body");
    }

    @Override
    public void prependPostToList(Post post) {
        mAdapter.insertPost(post);
        recyclerView.scrollToPosition(0);
    }
}

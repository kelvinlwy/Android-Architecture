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

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.androidarchitecture.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Post> mPosts = new ArrayList<>();

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bindView(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public Post getItem(int position) {
        if (mPosts == null) return null;
        return mPosts.get(position);
    }

    public void removeItem(int position) {
        notifyItemRemoved(position);
        mPosts.remove(position);
    }

    public void updateItems(List<Post> posts) {
        if (mPosts == null) mPosts = new ArrayList<>();
        mPosts.clear();
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

    public void insertPost(Post post) {
        if (mPosts == null) mPosts = new ArrayList<>();
        mPosts.add(0, post);
        notifyItemInserted(0);
    }
}

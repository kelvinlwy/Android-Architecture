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
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidarchitecture.models.Post;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostViewHolder extends RecyclerView.ViewHolder {

    @BindView(android.R.id.text1)
    TextView tvText1;
    @BindView(android.R.id.text2)
    TextView tvText2;

    public PostViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false));
        ButterKnife.bind(this, itemView);
    }

    public PostViewHolder bindView(Post post) {
        if (post != null) {
            tvText1.setText(post.getTitle());
            tvText2.setText(post.getBody());
        }
        return this;
    }
}

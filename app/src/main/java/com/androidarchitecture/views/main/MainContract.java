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
import com.androidarchitecture.views.base.BaseContract;

import java.util.List;

public interface MainContract {
    interface View extends BaseContract.BaseView {
        void refreshList(List<Post> posts);

        void toggleProgressIndicator(boolean refresh);

        void prependPostToList(Post post);
    }

    interface Presenter extends BaseContract.BasePresenter<MainContract.View> {
        void getPostsList();

        void removePost(Post post);

        void addPost(String title, String body);
    }

    interface Interactor extends BaseContract.BaseInteractor {
        void getPosts(GetPostsCallback callback);

        void deletePost(Post post, DeletePostCallback callback);

        void addPost(Post post, AddPostCallback callback);

        interface GetPostsCallback {
            void onGetPostsPreExecute();

            void onGetPostsSuccess(List<Post> posts);

            void onGetPostsError();
        }

        interface DeletePostCallback {
            void onDeletePostPreExecute();

            void onDeletePostSuccess();

            void onDeletePostError();
        }

        interface AddPostCallback {
            void onAddPostPreExecute();

            void onAddPostSuccess(Post post);

            void onAddPostError();
        }
    }
}

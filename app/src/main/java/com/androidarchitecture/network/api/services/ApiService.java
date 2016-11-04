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

package com.androidarchitecture.network.api.services;

import com.androidarchitecture.models.Post;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {

    @GET("posts/")
    Observable<List<Post>> getPosts();

    @GET("posts/{post_id}/")
    Observable<ResponseBody> getSinglePost(
            @Path("post_id") int postId
    );

    @GET("comments/")
    Observable<ResponseBody> getCommentsByPost(
            @Query("postId") int postId
    );

    @GET("posts/")
    Observable<ResponseBody> getPostsByUser(
            @Query("userId") int userId
    );

    @POST("posts/")
    Observable<Post> addPost(
            @Body Post post
    );

    @FormUrlEncoded
    @PUT("posts/")
    Observable<ResponseBody> updatePost(
            @Field("id") int id,
            @Field("title") String title,
            @Field("body") String body,
            @Field("userId") int userId
    );

    @FormUrlEncoded
    @PATCH("posts/")
    Observable<ResponseBody> updatePostTitle(
            @Field("id") int id,
            @Field("title") String title
    );

    @DELETE("posts/{post_id}/")
    Observable<ResponseBody> deletePost(
            @Path("post_id") int postId
    );
}

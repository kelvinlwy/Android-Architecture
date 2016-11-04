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
package com.androidarchitecture.db;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class TestItem {
    @Id
    private Long _id;
    @NotNull
    @SerializedName("userId")
    private Integer userId;
    @NotNull
    @SerializedName("id")
    private Integer id;
    @NotNull
    @SerializedName("title")
    private String title;
    @NotNull
    @SerializedName("body")
    private String body;

    @Generated(hash = 1891879840)
    public TestItem(Long _id, @NotNull Integer userId, @NotNull Integer id,
                    @NotNull String title, @NotNull String body) {
        this._id = _id;
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Generated(hash = 1690607865)
    public TestItem() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

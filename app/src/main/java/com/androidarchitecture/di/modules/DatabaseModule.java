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

import android.content.Context;

import com.androidarchitecture.BuildConfig;
import com.androidarchitecture.db.DaoMaster;
import com.androidarchitecture.db.DaoSession;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private final String mDbName;
    private boolean mEncrypted = true;

    public DatabaseModule(boolean isEncrypted) {
        this.mEncrypted = isEncrypted;
        this.mDbName = isEncrypted ? BuildConfig.DB_NAME_ENCRYPTED : BuildConfig.DB_NAME_DEV;
    }

    @Provides
    @Singleton
    DaoMaster.DevOpenHelper provideDaoMasterDevOpenHelper(Context context) {
        return new DaoMaster.DevOpenHelper(context, this.mDbName) {
            @Override
            public void onUpgrade(Database db, int oldVersion, int newVersion) {
                //  super.onUpgrade(db, oldVersion, newVersion);
                // Use db.execSQL(...) to execute SQL for schema updates
            }
        };
    }

    /**
     * @param helper Database helper
     * @return initial Database
     * @see <a href="http://greenrobot.org/greendao/documentation/database-encryption/">Database Encryption</a>
     */
    @Provides
    @Singleton
    Database provideDatabase(DaoMaster.DevOpenHelper helper) {
        return this.mEncrypted ? helper.getEncryptedWritableDb(BuildConfig.DB_PASSWORD) : helper.getWritableDb();
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(Database database) {
        return new DaoMaster(database).newSession();
    }
}

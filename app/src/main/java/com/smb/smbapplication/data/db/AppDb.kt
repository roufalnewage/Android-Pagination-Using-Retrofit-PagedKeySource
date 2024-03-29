/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smb.smbapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smb.smbapplication.data.model.User

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.comklklk
 */


/**rt
 * Main database description.klk;l
 */
@Database(
    entities = [User::class],
    version = 3,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun umsDao(): UMSDao
}

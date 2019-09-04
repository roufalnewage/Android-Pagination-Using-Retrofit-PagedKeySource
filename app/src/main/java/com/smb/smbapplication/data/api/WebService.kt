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

package com.smb.smbapplication.data.api

import androidx.lifecycle.LiveData
import com.smb.smbapplication.data.model.SearchModel
import com.smb.smbapplication.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */


/**
 * REST API access points
 */
interface WebService {

    var DEFAULT_PER_PAGE: Int
        get() = 25
        set(value) = TODO()
    /* @GET("search/repositories")
     fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<User>>*/


    @GET("api/speciality_list")

    fun loadUsers(): LiveData<ApiResponse<BaseResponse<List<User>>>>



    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/search/repositories")
    abstract fun getSearchResults(
            @Query("q") searchQuery: String,
            @Query("page") page: Int,
            @Query("per_page") per_page: Int
    ): LiveData<ApiResponse<SearchModel>>
}

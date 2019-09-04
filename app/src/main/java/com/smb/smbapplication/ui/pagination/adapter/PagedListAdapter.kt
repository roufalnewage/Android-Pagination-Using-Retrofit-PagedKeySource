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

package com.smb.smbapplication.ui.pagination.adapter

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.smb.smbapplication.AppExecutors
import com.smb.smbapplication.R
import com.smb.smbapplication.data.model.SearchItem
import com.smb.smbapplication.databinding.ItemSearchBinding
import com.smb.smbapplication.ui.BaseDataBindPagedListAdapter

/**
 * A RecyclerView adapter for [Repo] class.
 */
class PagedListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val itemClickCallback: ((SearchItem) -> Unit)?
) : BaseDataBindPagedListAdapter<SearchItem, ItemSearchBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return  oldItem.name == newItem.name
            }
        }
) {

    override fun createBinding(parent: ViewGroup): ItemSearchBinding {
        val binding = DataBindingUtil.inflate<ItemSearchBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_search,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
           /* binding.searchData?.let {
                itemClickCallback?.invoke(it)
            }*/
        }
        return binding
    }

    override fun bind(binding: ItemSearchBinding, item: SearchItem) {
        binding.searchData = item
    }
}

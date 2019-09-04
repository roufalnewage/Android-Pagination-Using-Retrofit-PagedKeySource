package com.smb.smbapplication.ui.pagination.datasource

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PageKeyedDataSource

import com.smb.smbapplication.data.api.Status
import com.smb.smbapplication.data.model.RequestFailure
import com.smb.smbapplication.data.model.SearchItem
import com.smb.smbapplication.data.model.SearchModel
import com.smb.smbapplication.databinding.FragmentLoginBinding
import com.smb.smbapplication.ui.RetryCallback
import com.smb.smbapplication.ui.login.LoginViewModel
import com.squareup.leakcanary.Retryable

class PagedListDataSource(private val mViewModel: LoginViewModel,
                          private val fragment: Fragment,
                          private val mBinding: FragmentLoginBinding) : PageKeyedDataSource<Int, SearchItem>() {
    private val requestFailureLiveData: MutableLiveData<RequestFailure>

    init {
        this.requestFailureLiveData = MutableLiveData()
    }

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, SearchItem>) {

        // Initial page
        val page = 1

        // `params.requestedLoadSize` is chosen based on the options provided in PagedList.Config
        // while setting up PagedList.Builder. It'll use the `InitialLoadSizeHint` value if provided
        // or the `pageSize` value if not.

        setSearchObserver(page,callback,null,true)

    }
    private fun setSearchObserver(PAGE: Int, callbackInit: LoadInitialCallback<Int, SearchItem>?, callbackAfter: LoadCallback<Int, SearchItem>?, isInit:Boolean ) {

        mViewModel.loadSearchData(PAGE)
        mViewModel.searchRepositories?.removeObservers(fragment)
        mBinding.callback = object : RetryCallback {
            override fun retry() {
                mViewModel.retrySearchData()
            }
        }
        mViewModel.searchRepositories.observe(fragment, Observer {

            if (it == null || it.data == null || it.status == Status.LOADING || it.data == null|| it.data.searchItems == null) {
                return@Observer
            } else {
                mViewModel.saveSearchDatanull()
                Log.i("NNNNN",it.data.toString())
                if(isInit) {
                    callbackInit!!.onResult(
                            it.data.searchItems as MutableList<SearchItem>, // List of data items
                            0, // Position of first item
                            it.data!!.totalCount!! , //bookingListModel.getc() Total number of items that can be fetched from api
                            null, // Previous page. `null` if there's no previous page
                            PAGE + 1 // Next Page (Used at the next request). Return `null` if this is the last page.
                    )
                }
                else {
                    callbackAfter!!.onResult(
                            it.data.searchItems as MutableList<SearchItem>,
                            // List of data items
                            // Next Page key (Used at the next request).
                            // Return `null` if this is the last page.
                            PAGE + 1
                    )
                }


            }


        })

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, SearchItem>) {
        // This is not necessary in our case as our data doesn't change. It's useful in cases where
        // the data changes and we need to fetch our list starting from the middle.
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, SearchItem>) {

        // Next page.
        val page = params.key
        Log.i("PAGEEE",""+page);
        setSearchObserver(page,null,callback,false)
        // `params.requestedLoadSize` is the `pageSize` value provided while setting up PagedList.Builder

                // Result can be passed asynchronously


    }

    fun getRequestFailureLiveData(): LiveData<RequestFailure> {
        return requestFailureLiveData
    }

    private fun handleError(retryable: Retryable, t: Throwable) {
        requestFailureLiveData.postValue(RequestFailure(retryable, t.message!!))
    }
}
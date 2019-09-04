package com.smb.smbapplication.ui.pagination

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.smb.smbapplication.AppExecutors

import com.smb.smbapplication.R
import com.smb.smbapplication.common.autoCleared
import com.smb.smbapplication.data.model.SearchItem
import com.smb.smbapplication.data.model.SearchModel
import com.smb.smbapplication.databinding.FragmentLoginBinding
import com.smb.smbapplication.ui.BaseFragment
import com.smb.smbapplication.ui.login.LoginFragmentDirections
import com.smb.smbapplication.ui.login.LoginViewModel
import com.smb.smbapplication.ui.pagination.adapter.PagedListAdapter
import com.smb.smbapplication.ui.pagination.datasource.PagedListDataSource
import com.smb.smbapplication.utils.logger.Log
import javax.inject.Inject

private const val TAG: String = "LoginFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class PaginationFragment : BaseFragment<FragmentLoginBinding>() {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: LoginViewModel

    var adapter by autoCleared<PagedListAdapter>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_login;
    }

    var dataList: PagedList<SearchItem>? = null
    var DEFAULT_PER_PAGE=25


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = getViewModel(LoginViewModel::class.java)

        mViewModel.loadSearchData(1)
        mViewModel.searchRepositories?.removeObservers(this@PaginationFragment)
        // Initialize Data Source
        val dataSource = PagedListDataSource(mViewModel, this@PaginationFragment, mBinding)
        val config = PagedList.Config.Builder()
                // Number of items to fetch at once. [Required]
                .setPageSize(DEFAULT_PER_PAGE)
                // Number of items to fetch on initial load. Should be greater than Page size. [Optional]
                //.setInitialLoadSizeHint(DEFAULT_PER_PAGE * 2)
                .setEnablePlaceholders(true) // Show empty views until data is available
                .build()
       // Build PagedList
        dataList = PagedList.Builder(dataSource, config) // Can pass `pageSize` directly instead of `config`
                // Do fetch operations on the main thread. We'll instead be using Retrofit's
                // built-in enqueue() method for background api calls.
                .setFetchExecutor(appExecutors.mainThread())
                // Send updates on the main thread
                .setNotifyExecutor(appExecutors.mainThread())
                .build()

        // Ideally, the above code should be placed in a ViewModel class so that the list can be
        // retained across configuration changes.
        // Required only once. Paging will handle fetching and updating the list.



        adapter = PagedListAdapter(dataBindingComponent = dataBindingComponent, appExecutors = appExecutors) {


            navController().navigate(
                    LoginFragmentDirections.showRegistration()
            )
        }
//Log.i("adsd",(dataList as MutableList<SearchItem>).size.toString())
        adapter.submitList(dataList)
        mBinding.listUser.adapter = adapter
        //adapter = rvAdapter

        //mBinding.image = "https://cdn.freebiesupply.com/logos/large/2x/android-logo-png-transparent.png"





    }

    fun navController() = findNavController()
}

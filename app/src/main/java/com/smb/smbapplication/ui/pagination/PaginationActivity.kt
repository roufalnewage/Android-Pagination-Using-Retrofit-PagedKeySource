package com.smb.smbapplication.ui.pagination



/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */


import android.os.Bundle
import com.smb.smbapplication.R
import com.smb.smbapplication.ui.BaseActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class PaginationActivity : BaseActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}

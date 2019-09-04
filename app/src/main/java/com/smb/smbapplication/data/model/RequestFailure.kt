package com.smb.smbapplication.data.model


import com.squareup.leakcanary.Retryable

class RequestFailure(val retryable: Retryable, val errorMessage: String)

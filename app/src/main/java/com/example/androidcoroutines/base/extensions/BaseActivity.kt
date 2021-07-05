package com.example.androidcoroutines.base.extensions

import androidx.appcompat.app.AppCompatActivity
import com.example.androidcoroutines.R

open class BaseActivity : AppCompatActivity() {

    private val loadingDialog by lazy {
        loading { messageRes = R.string.loading_text }.build()
    }

    fun showLoading() {
        loadingDialog.show()
    }

    fun hideLoading() {
        loadingDialog.dismiss()
    }
}

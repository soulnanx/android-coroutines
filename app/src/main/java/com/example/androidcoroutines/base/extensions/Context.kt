package com.example.androidcoroutines.base.extensions


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.androidcoroutines.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.loading(init: CustomLoadingDialog.() -> Unit): CustomLoadingDialog {
    return CustomLoadingDialog(this).apply { init() }
}

class CustomLoadingDialog(
    private val context: Context
) {
    var messageRes: Int = 0

    private fun getView(): View {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return layoutInflater.inflate(R.layout.loading_dialog, null).apply {
            findViewById<TextView>(R.id.label).setText(messageRes)
        } ?: View(context)
    }

    private fun buildDialog(): AlertDialog = MaterialAlertDialogBuilder(context)
        .setView(getView())
        .setCancelable(false)
        .create()

    fun build() = buildDialog()
}
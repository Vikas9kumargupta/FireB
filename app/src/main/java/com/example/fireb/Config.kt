package com.example.fireb

import android.app.AlertDialog
import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Config {

        private var dialog : AlertDialog? = null

        fun showDialog(context : Context){
            dialog = AlertDialog.Builder(context)
                .setView(R.layout.dialog_wait)
                .setCancelable(false)
                .create()

            dialog!!.show()
        }
    fun hideDialog(){
        dialog?.dismiss()
    }

}
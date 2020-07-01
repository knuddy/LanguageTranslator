package com.knuddj1languagetranslator.helpers.translator

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.knuddj1languagetranslator.R

class ProgressSpinner(context: Context) {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val view: View = inflater.inflate(R.layout.progress_spinner, null)
    private val dialog = Dialog(context)

    fun show(){
        dialog.setContentView(view)
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }
}
package com.knuddj1languagetranslator.fragments

import android.os.Bundle
import android.os.Process.killProcess
import android.os.Process.myPid
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.knuddj1languagetranslator.R
import kotlinx.android.synthetic.main.dialog_fragement_exit.*
import kotlin.system.exitProcess

class ExitDialogFragment : DialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragement_exit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCancel.setOnClickListener{dismiss()}
        btnClose.setOnClickListener{
            killProcess(myPid());
            exitProcess(1);
        }
    }
}
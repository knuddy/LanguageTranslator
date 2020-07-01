package com.knuddj1languagetranslator.activities.bases

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.toolbar.*


@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {
    companion object{
        const val MY_PREFERENCES = "nightModePrefs"
        const val KEY_ISNIGHTMODE = "isNightMode"
    }

    private var startDayNightSetting: Int = AppCompatDelegate.getDefaultNightMode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    internal fun displayToolbar(enableHome: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }

    override fun onRestart() {
        super.onRestart()
        if (AppCompatDelegate.getDefaultNightMode() != startDayNightSetting)
            recreate()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



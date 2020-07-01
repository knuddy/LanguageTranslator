package com.knuddj1languagetranslator.activities.menu

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.activities.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        displayToolbar(true)
        switchSettingsDarkMode.setOnCheckedChangeListener(SettingSwitchOnCheckChangedListener())
        updateDarkModeSwitch()
    }

    inner class SettingSwitchOnCheckChangedListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            if(isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            updateDarkModeSwitch()
        }
    }

    private fun updateDarkModeSwitch(){
        switchSettingsDarkMode.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
    }
}

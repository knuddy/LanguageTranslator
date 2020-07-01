package com.knuddj1languagetranslator.activities.bases

import android.annotation.SuppressLint
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.activities.menu.SettingsActivity
import com.knuddj1languagetranslator.fragments.ExitDialogFragment

@SuppressLint("Registered")
open class MenuBaseActivity: BaseActivity() {
    private fun showExitDialog(){
        val dialogFragment = ExitDialogFragment()
        dialogFragment.show(supportFragmentManager, null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_exit -> {
                showExitDialog()
                true
            }
            R.id.action_settings ->{
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
package com.knuddj1languagetranslator.activities.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.activities.quiz.QuizHubActivity
import com.knuddj1languagetranslator.activities.translator.TranslatorHubActivity
import com.knuddj1languagetranslator.activities.bases.MenuBaseActivity
import com.knuddj1languagetranslator.helpers.main.MainMenuItem
import com.knuddj1languagetranslator.helpers.main.MainMenuRecyclerViewAdapter
import com.knuddj1languagetranslator.helpers.RecyclerItemOnClickListener
import com.knuddj1languagetranslator.interfaces.IRecyclerViewItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MenuBaseActivity(), IRecyclerViewItem {

    private lateinit var mainMenuRecyclerViewAdapter: MainMenuRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayToolbar(false)

        mainMenuRecyclerViewAdapter = MainMenuRecyclerViewAdapter(ArrayList())
        revMenu.layoutManager = LinearLayoutManager(this@MainActivity)
        revMenu.addOnItemTouchListener(
            RecyclerItemOnClickListener(
                this@MainActivity,
                revMenu,
                this
            )
        )
        revMenu.adapter = mainMenuRecyclerViewAdapter

        addItem(getString(R.string.main_translator_card_title), getString(R.string.main_translator_card_secondary),
                R.drawable.books, Intent(this@MainActivity, TranslatorHubActivity::class.java))
        addItem(getString(R.string.main_quiz_card_title), getString(R.string.main_quiz_card_secondary),
                R.drawable.puzzle, Intent(this@MainActivity, QuizHubActivity::class.java))

    }

    private fun addItem(title: String, secondary: String, image: Int, intent: Intent){
        mainMenuRecyclerViewAdapter.addItem(
            MainMenuItem(
                title,
                secondary,
                image,
                intent
            )
        )
    }

    override fun onItemClick(view: View, position: Int) {
        val menuItem: MainMenuItem? = mainMenuRecyclerViewAdapter.getItem(position)
        if (menuItem != null){
            startActivity(menuItem.intent)
        }
    }
}

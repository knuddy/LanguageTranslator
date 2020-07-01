package com.knuddj1languagetranslator.activities.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.activities.bases.MenuBaseActivity
import com.knuddj1languagetranslator.helpers.RecyclerItemOnClickListener
import com.knuddj1languagetranslator.helpers.quiz.Quiz
import com.knuddj1languagetranslator.helpers.quiz.QuizAsyncTask
import com.knuddj1languagetranslator.helpers.quiz.QuizHubRecyclerViewAdapter
import com.knuddj1languagetranslator.interfaces.IDataAvailable
import com.knuddj1languagetranslator.interfaces.IRecyclerViewItem
import kotlinx.android.synthetic.main.activity_quiz_hub.*
import kotlin.collections.ArrayList

class QuizHubActivity : MenuBaseActivity(), IRecyclerViewItem, IDataAvailable {

    private lateinit var quizRecyclerViewAdapter: QuizHubRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_hub)
        displayToolbar(true)

        quizRecyclerViewAdapter = QuizHubRecyclerViewAdapter(ArrayList(), this)
        quizMenu.layoutManager = LinearLayoutManager(this@QuizHubActivity)
        quizMenu.addOnItemTouchListener(
            RecyclerItemOnClickListener(
                this@QuizHubActivity,
                quizMenu,
                this
            )
        )
        quizMenu.adapter = quizRecyclerViewAdapter

        val quizAsyncTask = QuizAsyncTask(this, this)
        quizAsyncTask.execute("quizsets-" + getString(R.string.countryCode) + ".json")

        setQuizActive(false)
    }

    private fun setQuizActive(active: Boolean){
        getSharedPreferences("quizActive", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("quizActive", active)
            .apply()
    }

    private fun getQuizActive() : Boolean{
        return getSharedPreferences("quizActive", Context.MODE_PRIVATE).getBoolean("quizActive", false)
    }

    override fun onItemClick(view: View, position: Int) {
        val quiz: Quiz? = quizRecyclerViewAdapter.getQuiz(position)
        if (quiz != null){
            setQuizActive(true)
            val intent = Intent(this@QuizHubActivity, QuizManagerActivity::class.java)
            intent.putExtra("quiz", quiz)
            startActivity(intent)
        }
    }

    override fun onDataAvailable(data: ArrayList<Quiz>) {
        quizRecyclerViewAdapter.loadNewData(data)
    }

    override fun onError(e: Exception) {
        Log.d("Quiz Manager Activity", getString(R.string.on_error, e.message))
    }

    override fun onResume() {
        super.onResume()
        if(getQuizActive()) {
            setQuizActive(false)
            quizRecyclerViewAdapter.notifyDataSetChanged()
        }
    }
}

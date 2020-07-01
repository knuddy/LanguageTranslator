package com.knuddj1languagetranslator.activities.quiz


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.activities.bases.MenuBaseActivity
import com.knuddj1languagetranslator.fragments.QuestionFeedbackFragment
import com.knuddj1languagetranslator.fragments.QuizQuestionFragment
import com.knuddj1languagetranslator.helpers.quiz.Quiz
import com.knuddj1languagetranslator.interfaces.IQuizQuestionComplete
import kotlinx.android.synthetic.main.activity_quiz_manager.*


class QuizManagerActivity : MenuBaseActivity(), IQuizQuestionComplete{
    private lateinit var quiz: Quiz
    private var quizScore = 0
    private var currentQuestion = 0
    private var fragManager: FragmentManager = supportFragmentManager
    private lateinit var quizQuestionFragment: QuizQuestionFragment
    private lateinit var questionFeedbackFragment: QuestionFeedbackFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_manager)
        displayToolbar(true)

        quiz = intent.getParcelableExtra("quiz") as Quiz
        txvQuizTitle.text = quiz.quizName
        txvHighScoreValue.text = getSharedPreferences(quiz.quizName, Context.MODE_PRIVATE).getInt(quiz.quizName, 0).toString()
        btnStartQuiz.setOnClickListener(StartQuizOnClickListener())
    }

    private fun loadQuestion(){
        if(currentQuestion < quiz.quizQuestions.size){
            quizQuestionFragment = QuizQuestionFragment(quiz.quizQuestions[currentQuestion], this)
            currentQuestion++
            fragManager.beginTransaction()
                .replace(R.id.fragment, quizQuestionFragment)
                .commit()
        }
        else{
            var sharedPrefs = getSharedPreferences(quiz.quizName, Context.MODE_PRIVATE)
            if(quizScore > sharedPrefs.getInt(quiz.quizName, 0)){
                sharedPrefs.edit().putInt(quiz.quizName, quizScore).apply()
            }
            for (fragment in fragManager.fragments) {
                fragManager.beginTransaction().remove(fragment).commit()
            }
            btnStartQuiz.isEnabled = true
            currentQuestion = quiz.quizQuestions.size - 1
            fragment.visibility = View.GONE
            expandableView.visibility = View.VISIBLE
            txvHighScoreValue.text = getSharedPreferences(quiz.quizName, Context.MODE_PRIVATE).getInt(quiz.quizName, 0).toString()
        }
    }

    override fun onQuestionOptionSelected(isCorrect: Boolean) {
        quizScore += if(isCorrect) 1 else 0
        questionFeedbackFragment = QuestionFeedbackFragment(isCorrect, this)
        fragManager.beginTransaction()
            .replace(R.id.fragment, questionFeedbackFragment)
            .commit()
    }

    override fun onFeedBackClosed() {
        loadQuestion()
    }

    inner class StartQuizOnClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            loadQuestion()
            expandableView.visibility = View.GONE
            fragment.visibility = View.VISIBLE
        }
    }
}

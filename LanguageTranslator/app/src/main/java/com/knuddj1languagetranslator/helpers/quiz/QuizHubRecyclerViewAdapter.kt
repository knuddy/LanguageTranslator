package com.knuddj1languagetranslator.helpers.quiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knuddj1languagetranslator.R

class QuizHubRecyclerViewAdapter(private var quizList: ArrayList<Quiz>, private var context: Context)
    :RecyclerView.Adapter<QuizViewHolder>(){

    override fun getItemCount(): Int {
        return if(quizList.isNotEmpty()) quizList.size else 0
    }

    fun loadNewData(newQuizList: ArrayList<Quiz>){
        quizList = newQuizList
        notifyDataSetChanged()
    }

    fun getQuiz(position: Int): Quiz? {
        return if (quizList.isNotEmpty()) quizList[position] else null
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz: Quiz = quizList[position]
        holder.txvQuizTitle.text = quiz.quizName
        holder.txvQuizHighScore.text = context.getSharedPreferences(quiz.quizName, Context.MODE_PRIVATE).getInt(quiz.quizName, 0).toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.quiz_recycler_view_item, parent, false)
        return QuizViewHolder(view)
    }

}
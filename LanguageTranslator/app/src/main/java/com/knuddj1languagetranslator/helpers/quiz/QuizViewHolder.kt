package com.knuddj1languagetranslator.helpers.quiz

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.knuddj1languagetranslator.R

class QuizViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var txvQuizTitle: TextView = view.findViewById(R.id.txvQuizTitle)
    var txvQuizHighScore: TextView = view.findViewById(R.id.txvQuizHighScore)
}
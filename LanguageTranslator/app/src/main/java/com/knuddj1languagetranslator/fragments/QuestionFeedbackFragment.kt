package com.knuddj1languagetranslator.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.interfaces.IQuizQuestionComplete
import kotlinx.android.synthetic.main.fragment_question_feedback.view.*

class QuestionFeedbackFragment(private var isCorrect: Boolean, private var listener: IQuizQuestionComplete) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_question_feedback, container, false)
        view.txvFeedback.text = if(isCorrect) "Correct" else "Incorrect"
        view.background = context?.getDrawable(if(isCorrect) android.R.color.holo_green_light else android.R.color.holo_red_light)
        Handler().postDelayed({
            listener.onFeedBackClosed()
        }, resources.getInteger(R.integer.sleep).toLong() )
        return view
    }
}

package com.knuddj1languagetranslator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.knuddj1languagetranslator.R
import com.knuddj1languagetranslator.helpers.quiz.QuizQuestion
import com.knuddj1languagetranslator.interfaces.IQuizQuestionComplete
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_quiz_question.view.*

class QuizQuestionFragment(private var question: QuizQuestion, private var listener: IQuizQuestionComplete) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_quiz_question, container, false)
        view.txvQuestionText.text = question.questionText



        view.txvChoiceOne.text = question.questionOptions[0]
        view.txvChoiceOne.setOnClickListener(QuizChoiceOnClickListener(question.questionAnswer == 0))

        view.txvChoiceTwo.text = question.questionOptions[1]
        view.txvChoiceTwo.setOnClickListener(QuizChoiceOnClickListener(question.questionAnswer == 1))

        view.txvChoiceThree.text = question.questionOptions[2]
        view.txvChoiceThree.setOnClickListener(QuizChoiceOnClickListener(question.questionAnswer == 2))

        view.txvChoiceFour.text = question.questionOptions[3]
        view.txvChoiceFour.setOnClickListener(QuizChoiceOnClickListener(question.questionAnswer == 3))

        Picasso.with(view.imvQuestion.context).load(question.questionImageUrl)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.imvQuestion)
        return view
    }

    inner class QuizChoiceOnClickListener(private var isCorrect: Boolean) : View.OnClickListener{
        override fun onClick(v: View?) {
            listener.onQuestionOptionSelected(isCorrect)
        }
    }
}

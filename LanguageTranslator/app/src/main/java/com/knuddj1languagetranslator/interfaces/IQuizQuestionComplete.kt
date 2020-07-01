package com.knuddj1languagetranslator.interfaces

interface IQuizQuestionComplete {
    fun onQuestionOptionSelected(isCorrect: Boolean)
    fun onFeedBackClosed()
}
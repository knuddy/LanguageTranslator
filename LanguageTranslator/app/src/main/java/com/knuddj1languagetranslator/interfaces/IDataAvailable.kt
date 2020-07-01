package com.knuddj1languagetranslator.interfaces

import com.knuddj1languagetranslator.helpers.quiz.Quiz

interface IDataAvailable {
    fun onDataAvailable(data: ArrayList<Quiz>)
    fun onError(e: Exception)
}
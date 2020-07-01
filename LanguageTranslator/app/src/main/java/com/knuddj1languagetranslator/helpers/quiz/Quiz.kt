package com.knuddj1languagetranslator.helpers.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Quiz (
    var quizName: String,
    var quizQuestions: ArrayList<QuizQuestion>
) : Parcelable
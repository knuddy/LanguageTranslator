package com.knuddj1languagetranslator.helpers.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class QuizQuestion (
    var questionImageUrl: String,
    var questionText: String,
    var questionOptions: ArrayList<String>,
    var questionAnswer: Int
) : Parcelable

package com.knuddj1languagetranslator.helpers.main

import android.content.Intent

data class MainMenuItem (
    var title: String,
    var secondary: String,
    var image: Int,
    var intent: Intent
)
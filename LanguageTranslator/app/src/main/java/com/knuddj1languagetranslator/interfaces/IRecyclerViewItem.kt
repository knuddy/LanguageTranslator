package com.knuddj1languagetranslator.interfaces

import android.view.View

interface IRecyclerViewItem {
    fun onItemClick(view: View, position: Int)
}
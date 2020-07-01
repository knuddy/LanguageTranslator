package com.knuddj1languagetranslator.helpers.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.knuddj1languagetranslator.R

class MenuItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var imvCard: ImageView = view.findViewById(R.id.imvCard)
    var txvTitle: TextView = view.findViewById(R.id.txvTitle)
    var txvSecondary: TextView = view.findViewById(R.id.txvSecondary)
}
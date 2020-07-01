package com.knuddj1languagetranslator.helpers.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knuddj1languagetranslator.R
import com.squareup.picasso.Picasso


class MainMenuRecyclerViewAdapter(private var mainMenuItems: ArrayList<MainMenuItem>)
    : RecyclerView.Adapter<MenuItemViewHolder>(){

    override fun getItemCount(): Int {
        return if (mainMenuItems.isNotEmpty()) mainMenuItems.size else 0
    }

    fun addItem(mainMenuItem: MainMenuItem){
        mainMenuItems.add(mainMenuItem)
        notifyDataSetChanged()
    }

    fun getItem(position: Int) : MainMenuItem? {
        return if (mainMenuItems.isNotEmpty()) mainMenuItems[position] else null
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val mainMenuItem: MainMenuItem = mainMenuItems[position]
        var context: Context = holder.imvCard.context

        Picasso.with(context)
            .load(mainMenuItem.image)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imvCard)

        holder.txvTitle.text = mainMenuItem.title
        holder.txvSecondary.text = mainMenuItem.secondary
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MenuItemViewHolder(
            view
        )
    }
}
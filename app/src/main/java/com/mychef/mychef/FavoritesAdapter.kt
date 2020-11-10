package com.mychef.mychef

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Need to provide a custom viewholder class
class FavoritesAdapter(private val list: List<Recipe>): RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CustomViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val recipe: Recipe = list[position]
        holder.bind(recipe)
    }
    override fun getItemCount(): Int = list.size
}

class CustomViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)){
    private var mTitleView: TextView? = null
    private var mCookTimeView: TextView? = null

    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mCookTimeView = itemView.findViewById(R.id.list_description)
    }

    fun bind(recipe: Recipe) {
        mTitleView?.text = recipe.title
        mCookTimeView?.text = recipe.timeToCook
    }

}
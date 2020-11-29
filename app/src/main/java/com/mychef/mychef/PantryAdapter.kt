package com.mychef.mychef

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pantry_list.view.*
import kotlinx.android.synthetic.main.search_item.view.*
lateinit var parentcontext: Context
// Need to provide a custom viewholder class
class PantryAdapter(private val list: List<FoodStuff>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        parentcontext = parent.context
        return ViewHolder(inflater, parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: FoodStuff = list[position]
        holder.bind(recipe)

        holder.itemView.imageButton4.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            mcontext.startActivity(intent)  }
    }

    override fun getItemCount(): Int = list.size
}

class ViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.pantry_list, parent, false)){
    private var mTitleView: TextView? = null
    private var mCookTimeView: TextView? = null
    private var mImageView: ImageView? = null

    init {
        mTitleView = itemView.findViewById(R.id.slist_title)
        mCookTimeView = itemView.findViewById(R.id.slist_description)
        mImageView = itemView.findViewById(R.id.imageView)
    }

    fun bind(recipe: FoodStuff) {
        mTitleView?.text = recipe.name
        mCookTimeView?.text = recipe.Quantity.toString()
        mImageView?.setImageResource(recipe.Photo)
    }
}


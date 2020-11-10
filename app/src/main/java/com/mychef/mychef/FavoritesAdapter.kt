package com.mychef.mychef

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

// Need to provide a custom viewholder class
class FavoritesAdapter(private val list: List<RecipeWI>): RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        mcontext = parent.context
        return CustomViewHolder(inflater, parent)
    }

    private fun changeFavorite(recipe: RecipeWI) {
        recipe.favorite = !recipe.favorite
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val recipe: RecipeWI = list[position]
        holder.bind(recipe)

        holder.itemView.imageButton.setOnClickListener{
            changeFavorite(recipe)
        }

        if(!recipe.favorite) {holder.itemView.visibility = View.GONE
            holder.itemView.imageButton.setColorFilter(R.color.colorHeartOff) }
        else {holder.itemView.imageButton.setColorFilter(R.color.colorHeartOn)}

        holder.itemView.imageButton4.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            intent.putExtra("passtitle", recipe.title)
            intent.putExtra("passtime", recipe.timeToCook)
            intent.putExtra("passyield", recipe.yields)
            intent.putExtra("passingredients", recipe.ingredients)
            intent.putExtra("passphoto", recipe.Photo)
            intent.putExtra("passdirections", recipe.directions)
            mcontext.startActivity(intent)  }
    }

    override fun getItemCount(): Int = list.size
}

class CustomViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)){
    private var mTitleView: TextView? = null
    private var mCookTimeView: TextView? = null
    private var mImageView: ImageView? = null

    init {
        mTitleView = itemView.findViewById(R.id.slist_title)
        mCookTimeView = itemView.findViewById(R.id.slist_description)
        mImageView = itemView.findViewById(R.id.imageView)
    }

    fun bind(recipe: RecipeWI) {
        mTitleView?.text = recipe.title
        mCookTimeView?.text = recipe.timeToCook
        mImageView?.setImageResource(recipe.Photo)
    }
}
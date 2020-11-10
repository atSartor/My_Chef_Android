package com.mychef.mychef

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_item.view.*
import java.util.*
import kotlin.collections.ArrayList
lateinit var mcontext: Context
// Need to provide a custom viewholder class
class SearchAdapter(private val list: List<RecipeWI>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        mcontext = parent.context
        return MyViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe: RecipeWI = list[position]
        holder.bind(recipe)

        holder.itemView.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            intent.putExtra("passtitle", recipe.title)
            intent.putExtra("passtime", recipe.timeToCook)
            intent.putExtra("passyield", recipe.yields)
            intent.putExtra("passingredients", recipe.ingredients)
            intent.putExtra("passphoto", recipe.Photo)
            mcontext.startActivity(intent)  }
    }

    override fun getItemCount(): Int = list.size
    /*override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = list
                } else {
                    val resultList = mutableListOf<Recipe>()
                    for (row in list) {
                        if (row.title.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as MutableList<Recipe>
                notifyDataSetChanged()
            }

        }
    }*/
}

class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.search_item, parent, false)){
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


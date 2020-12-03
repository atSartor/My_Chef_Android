package com.mychef.mychef

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_pantry.view.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.new_item.view.*
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

    private fun addQuant(recipe: FoodStuff) {
        recipe.Quantity = recipe.Quantity + 1
    }
    private fun subQuant(recipe: FoodStuff) {
        if(recipe.Quantity == 0) {
            deleteItem(recipe)
        }
        else {
            recipe.Quantity = recipe.Quantity - 1
        }
    }
    private fun deleteItem(recipe: FoodStuff) {
        val newList = list - recipe
        PantryAdapter(newList)
    }
    /*private fun clearList() {
        pantryList = emptyList()
    }
    private fun addItem() {
        PantryAdapter(ingredientList)
    }
    private fun newItem(recipe: FoodStuff) {
        pantryList + recipe
        PantryAdapter(pantryList)
    }*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: FoodStuff = list[position]
        holder.bind(recipe)

        holder.itemView.imageButton.setOnClickListener {
            addQuant(recipe)
        }
        holder.itemView.minusButton.setOnClickListener {
            subQuant(recipe)
        }
        /*holder.itemView.imageButton4.setOnClickListener {
            deleteItem(recipe)
        }
        holder.itemView.button5.setOnClickListener {
            clearList()
        }
        holder.itemView.button.setOnClickListener {
            addItem()
        }
        holder.itemView.imageButton5.setOnClickListener {
            newItem(recipe)
        }*/


        /*holder.itemView.minusButton.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            mcontext.startActivity(intent)
            pantryList = listOf(
                FoodStuff("Yellow Onion", 3, R.drawable.onion_yellow),
                FoodStuff("Linguine (1Lb)", 7, R.drawable.linguine_1lb),
                FoodStuff("Red Chili Flakes", 2, R.drawable.chili_flakes)
            )
        }
        holder.itemView.imageButton4.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            mcontext.startActivity(intent)
            pantryList = listOf(
                FoodStuff("Linguine (1Lb)", 7, R.drawable.linguine_1lb),
                FoodStuff("Red Chili Flakes", 2, R.drawable.chili_flakes)
            )
        }
        holder.itemView.button5.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            mcontext.startActivity(intent)
            mcontext.startActivity(intent)
            pantryList = emptyList()
        }
        holder.itemView.button.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            mcontext.startActivity(intent)
            mcontext.startActivity(intent)
            pantryList = ingredientList
        }
        holder.itemView.imageButton5.setOnClickListener{
            val intent = Intent(mcontext, DetailsActivity::class.java)
            mcontext.startActivity(intent)
            mcontext.startActivity(intent)
            pantryList = listOf(
                FoodStuff("Linguine (1Lb)", 7, R.drawable.linguine_1lb),
                FoodStuff("Red Chili Flakes", 2, R.drawable.chili_flakes),
                FoodStuff("Paprika (Tablespoon)", 1, R.drawable.paprika)
            )
        }*/
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


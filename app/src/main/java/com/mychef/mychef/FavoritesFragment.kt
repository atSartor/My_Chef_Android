package com.mychef.mychef

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*

//DEMO DATA STRUCTURE
data class Recipe(val title: String, val timeToCook: String)

class FavoritesFragment: Fragment(R.layout.fragment_favorites) {
    // THIS IS DEMO DATA
    private val favoriteRecipes = listOf(
        RecipeWI("Lemon Pepper Chicken Breast", "45 Minutes", "2 servings",
            "2 chicken breasts \n1 Lemon \n2 tablespoons Lemon Pepper seasoning \nOlive Oil \nSalt \nPepper",
            R.drawable.chicken),
        RecipeWI("Spaghetti and Meatballs", "30 Minutes", "4 servings",
            "1 pound ground beef \n1 box cooked spaghetti \nParmesan \nItalian Bread Crumbs " +
                    "\nMarinara Sauce \nGarlic \nOregano \nSalt \nPepper", R.drawable.spaghetti)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize RecyclerView node
        favoritesRecyclerView.apply {
            // set a LinearLayoutManager to handle Android
            layoutManager = LinearLayoutManager(activity)
            adapter = FavoritesAdapter(favoriteRecipes)
        }

    }
}


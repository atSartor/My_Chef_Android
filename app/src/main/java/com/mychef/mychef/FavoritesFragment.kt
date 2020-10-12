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
        Recipe("Lemon Pepper Chicken Breast", "45 Minutes"),
        Recipe("Spaghetti and Meatballs", "30 Minutes"),
        Recipe("Chocolate Chip Cookies", "60 Minutes"),
        Recipe("Bacon and Egg Tacos", "20 Minutes"),
        Recipe("Buttermilk Waffles", "30 Minutes"),
        Recipe("Hello world", "Foo bar"),
        Recipe("Hello world", "Foo bar"),
        Recipe("Hello world", "Foo bar"),
        Recipe("Hello world", "Foo bar"),
        Recipe("Hello world", "Foo bar")
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


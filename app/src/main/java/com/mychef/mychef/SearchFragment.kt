package com.mychef.mychef

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
//import kotlinx.android.synthetic.main.fragment_search.my_search
import kotlinx.android.synthetic.main.fragment_search.searchRecyclerView

data class RecipeWI(val title: String, val timeToCook: String, val yields: String, val ingredients: String, val directions:String, val Photo: Int, var favorite: Boolean)

public val favoriteRecipes = listOf(
    RecipeWI("Lemon Pepper Chicken Breast", "45 Minutes", "2 servings",
        "2 chicken breasts \n1 Lemon \n2 tablespoons Lemon Pepper seasoning \nOlive Oil \nSalt \nPepper",
        "1. Preheat Oven To 425F \n2. Grease baking dish with Olive Oil \n" +
                "3. Place chicken in dish and season with lemon pepper seasoning, salt, and pepper \n" +
                "4. Slice 1 Lemon and place slices around chicken \n5.Bake for 35 minutes",
        R.drawable.chicken, true),
    RecipeWI("Spaghetti and Meatballs", "30 Minutes", "4 servings",
        "1 pound ground beef \n1 box cooked spaghetti \nParmesan \nItalian Bread Crumbs " +
                "\nMarinara Sauce \nGarlic \nOregano \nSalt \nPepper", "1. Preheat Oven To 350F \n2. Grease cookie sheet \n" +
                "3. Combine ground beef, parmesan, bread crumbs, garlic, oregano, salt, and pepper \n" +
                "4. Shape into 1 inch balls and place on cookie sheet \n5.Bake for 20 minutes, flipping halfway through \n" +
                "6. Add cooked noodles, meatballs, and marinara to a saucepan\n7.Sautee together for 5 minutes and serve", R.drawable.spaghetti, true),
    RecipeWI("Chocolate Chip Cookies", "60 Minutes", "8 servings","", "", R.drawable.cookies, false),
    RecipeWI("Bacon and Egg Tacos", "20 Minutes", "2 servings","", "", R.drawable.tacos, false),
    RecipeWI("Buttermilk Waffles", "30 Minutes", "4 servings","", "",R.drawable.waffles, false)
)

class SearchFragment: Fragment(R.layout.fragment_search) {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    /*private fun doMySearch(query: String):List<RecipeWI> {
        var recipes = listOf(RecipeWI("","","","","",1,false))
        val url = "https://mychef-web.herokuapp.com/api/v1/show_recipes?search=$query"
        //var i=1
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                recipes= listOf(RecipeWI("%s".format(response.getJSONObject(1).opt("title")),
                    "%s".format(response.getJSONObject(1).opt("total_time")),
                    "%s".format(response.getJSONObject(1).opt("yields")),
                    "","",R.drawable.spaghetti,false))
                //i++
            },
            Response.ErrorListener { error ->
                recipes= listOf(RecipeWI("That didn't work",
                    "Error",
                    "Try again",
                    "","",R.drawable.spaghetti,false))
            }
        )
        return recipes
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize RecyclerView node
        searchRecyclerView.apply {
            // set a LinearLayoutManager to handle Android
            layoutManager = LinearLayoutManager(activity)
            //var results = doMySearch("")
            adapter = SearchAdapter(favoriteRecipes)
            /*my_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    SearchAdapter(results).filter.filter(newText)
                    return false
                }

            })*/
        }



    }
}
package com.mychef.mychef

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
//import kotlinx.android.synthetic.main.fragment_search.my_search
import kotlinx.android.synthetic.main.fragment_search.searchRecyclerView

data class RecipeWI(val title: String, val timeToCook: String, val yields: String, val ingredients: String, val Photo: Int)

class SearchFragment: Fragment(R.layout.fragment_search) {
    // THIS IS DEMO DATA
    private val favoriteRecipes = listOf(
        RecipeWI("Lemon Pepper Chicken Breast", "45 Minutes", "2 servings",
            "2 chicken breasts \n1 Lemon \n2 tablespoons Lemon Pepper seasoning \nOlive Oil \nSalt \nPepper",
        R.drawable.chicken),
        RecipeWI("Spaghetti and Meatballs", "30 Minutes", "4 servings",
        "1 pound ground beef \n1 box cooked spaghetti \nParmesan \nItalian Bread Crumbs " +
                "\nMarinara Sauce \nGarlic \nOregano \nSalt \nPepper", R.drawable.spaghetti),
        RecipeWI("Chocolate Chip Cookies", "60 Minutes", "8 servings", "", R.drawable.cookies),
        RecipeWI("Bacon and Egg Tacos", "20 Minutes", "2 servings", "", R.drawable.tacos),
        RecipeWI("Buttermilk Waffles", "30 Minutes", "4 servings", "",R.drawable.waffles)
    )



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    /*private fun doMySearch(query: String):List<Recipe> {
        var recipes = emptyList<Recipe>() as MutableList<Recipe>
        val queue = Volley.newRequestQueue(this.context)
        val url = "https://mychef-web.herokuapp.com/api/v1/show_recipes?search=$query"
        var i=1
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                var title = "%s\n".format(response.getJSONObject(1).opt("title"))
                var other = "\t\t\tPreparation Time: %s\n".format(response.getJSONObject(1).opt("total_time")) +
                        "\t\t\tServings: %s\n".format(response.getJSONObject(1).opt("yields"))
                recipes.add(Recipe(title,other))
                //i++
            },
            Response.ErrorListener { error ->
                var title = "That didn't work!"
                var other = "Try Again"
                recipes.add(Recipe(title, other))
            }
        )
        queue.add(jsonArrayRequest)
        var results = listOf<Recipe>(recipes[0])
        return results
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize RecyclerView node
        searchRecyclerView.apply {
            // set a LinearLayoutManager to handle Android
            layoutManager = LinearLayoutManager(activity)

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
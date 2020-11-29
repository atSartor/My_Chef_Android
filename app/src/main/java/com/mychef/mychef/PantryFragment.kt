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
import kotlinx.android.synthetic.main.fragment_search.searchRecyclerView



data class FoodStuff(val name: String, val Quantity: Int,  val Photo: Int)

public val pantryList = listOf(
        FoodStuff("Yellow Onion", 3, R.drawable.onion_yellow),
        FoodStuff("Garlic Clove", 6, R.drawable.garlic_clove),
        FoodStuff("Linguine - 1LB", 1,  R.drawable.linguine_1lb)
    )
class PantryFragment: Fragment(R.layout.fragment_pantry) {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_pantry, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            //var results = doMySearch("")
            adapter = PantryAdapter(pantryList)
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
package com.mychef.mychef

import android.content.Intent
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
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.imageButton
import kotlinx.android.synthetic.main.pantry_list.*
import kotlinx.android.synthetic.main.search_item.view.*



data class FoodStuff(var name: String, var Quantity: Int,  var Photo: Int)

public var pantryList = listOf(
    FoodStuff("Yellow Onion", 3, R.drawable.onion_yellow),
    FoodStuff("Linguine (1Lb)", 7, R.drawable.linguine_1lb),
    FoodStuff("Red Chili Flakes", 2, R.drawable.chili_flakes),
    FoodStuff("Salt (Tablespoon)", 0, R.drawable.salt),
    FoodStuff("Pepper (Tablespoon)", 0, R.drawable.pepper),
    FoodStuff("Coriander (Tablespoon)", 0, R.drawable.coriander),
    FoodStuff("Paprika (Tablespoon)", 0, R.drawable.paprika),
    FoodStuff("Oregano (Tablespoon)", 0, R.drawable.oregano),
    FoodStuff("Turmeric (Tablespoon)", 0, R.drawable.turmeric),
    FoodStuff("Whole Nutmeg", 0, R.drawable.nutmeg),
    FoodStuff("Bay Leaf", 0, R.drawable.bay_leaf),
    FoodStuff("Cayenne Pepper", 0, R.drawable.cayenne),
    FoodStuff("Thyme", 0, R.drawable.thyme),
    FoodStuff("Cinnamon", 0, R.drawable.cinnamon),
    FoodStuff("Panko Breadcrumbs", 0, R.drawable.panko),
    FoodStuff("Couscous (10oz)", 0, R.drawable.couscous),
    FoodStuff("Rice (1 cup)", 0, R.drawable.rice),
    FoodStuff("All-purpose Flour (1 Cup)", 0, R.drawable.floiur),
    FoodStuff("Brown Sugar (1 Cup)", 0, R.drawable.sugar_brown),
    FoodStuff("Powdered Sugar (1 Cup)", 0, R.drawable.sugar_powder),
    FoodStuff("Sugar (1 Cup)", 0, R.drawable.sugar),
    FoodStuff("Chicken stock (1 Cup)", 0, R.drawable.stock_chicken),
    FoodStuff("Beef stock (1 Cup)", 0, R.drawable.stock_beef),
    FoodStuff("Butter", 0, R.drawable.butter),
    FoodStuff("Heavy cream", 0, R.drawable.heavy_cream),
    FoodStuff("Eggs", 0, R.drawable.eggs),
    FoodStuff("Parmesan Cheese", 0, R.drawable.parmesan),
    FoodStuff("Bacon", 0, R.drawable.bacon),
    FoodStuff("Parsley", 0, R.drawable.parsley),
    FoodStuff("Celery", 0, R.drawable.celery),
    FoodStuff("Carrots", 0, R.drawable.carrots),
    FoodStuff("Lemons", 0, R.drawable.lemons),
    FoodStuff("Limes", 0, R.drawable.limes),
    FoodStuff("Orange juice", 0, R.drawable.orange_juice),
    FoodStuff("Ketchup", 0, R.drawable.ketchup),
    FoodStuff("Mayonnaise", 0, R.drawable.mayo),
    FoodStuff("Extra virgin olive oil", 0, R.drawable.olive_oil),
    FoodStuff("vegetable oil", 0, R.drawable.vegetable_oil),
    FoodStuff("Canola oil", 0, R.drawable.canola_oil),
    FoodStuff("Vinegar", 0, R.drawable.vinegar),
    FoodStuff("Mustard", 0, R.drawable.mustard),
    FoodStuff("Honey", 0, R.drawable.honey),
    FoodStuff("Shallots", 0, R.drawable.shallots),
    FoodStuff("Potatoes — Idaho", 0, R.drawable.potato),
    FoodStuff("Onions — red", 0, R.drawable.onion_red),
    FoodStuff("Tomatoes", 0, R.drawable.tomatoes),
    FoodStuff("Tomatoes — diced", 0, R.drawable.tomatoes_diced),
    FoodStuff("Tomato sauce", 0, R.drawable.tomato_sauce),
    FoodStuff("Tomato paste", 0, R.drawable.tomato_paste),
    FoodStuff("Tomatoes — crushed", 0, R.drawable.tomatoes_crushed),
    FoodStuff("Black Beans", 0, R.drawable.beans_black),
    FoodStuff("Cauliflower", 0, R.drawable.cauliflower),
    FoodStuff("Green beans", 0, R.drawable.beans_green),
    FoodStuff("Milk", 0, R.drawable.milk),
    FoodStuff("Ground Beef", 0, R.drawable.beef),
    FoodStuff("Chicken Breast", 0, R.drawable.chicken_breast),
    FoodStuff("Pork Tenderloin", 0, R.drawable.pork_tender),
    FoodStuff("Worcestershire Sauce", 0, R.drawable.worc_sauce),
    FoodStuff("Almonds", 0, R.drawable.almonds),
    FoodStuff("Cheddar Cheese", 0, R.drawable.cheddar),
    FoodStuff("Brown Rice", 0, R.drawable.brown_rice),
    FoodStuff("Pinto Beans", 0, R.drawable.beans_pinto),
    FoodStuff("Yogurt", 0, R.drawable.yogurt),
    FoodStuff("Sweet Potatoes", 0, R.drawable.sweet_potato),
    FoodStuff("Maple Syrup", 0, R.drawable.maple_syrup),
    FoodStuff("Tuna", 0, R.drawable.tuna),
    FoodStuff("Broccoli", 0, R.drawable.broccoli),
    FoodStuff("Red Wine", 0, R.drawable.wine),
    FoodStuff("Corn", 0, R.drawable.corn),
    FoodStuff("Portabello Mushrooms", 0, R.drawable.mushrooms),
    FoodStuff("Strawberries", 0, R.drawable.strawberries),
    FoodStuff("Blueberries", 0, R.drawable.blueberries),
    FoodStuff("Shrimp", 0, R.drawable.shrimp)
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
        }
    }

}

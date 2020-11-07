package com.mychef.mychef

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val favoritesFragment = FavoritesFragment()
        val pantryFragment = PantryFragment()
        val listFragment = ListFragment()
        val moreFragment = MoreFragment()
        val searchFragment = SearchFragment()
        val loginActivity = LoginActivity()

        setCurrentFragment(favoritesFragment)

        bottomNavigationView2.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miFavorites -> setCurrentFragment(favoritesFragment)
                R.id.miPantry -> setCurrentFragment(pantryFragment)
                R.id.miList -> setCurrentFragment(listFragment)
                R.id.miMore -> setCurrentFragment(moreFragment)
                R.id.miSearch -> startSearch(null, false, null, false)
            }
            true
        }

        /*val button = findViewById<Button>(R.id.search_button)
        button.setOnClickListener {
            onSearchRequested()
        }*/
        val listView = findViewById<ListView>(R.id.main_listView)
        listView.adapter = CustomAdapter(this)
    }

    private class CustomAdapter(context: Context): BaseAdapter() {

        private val mContext: Context
        private val items = arrayListOf<String> (
                "Onion" , "Carrot" , "Potato" , "Celery"
            )

        init{
            this.mContext = context
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.main_row, viewGroup, false)
            val nameTextView = rowMain.findViewById<TextView>(R.id.name_textview)
            nameTextView.text = items.get(position)
            val positionTextView = rowMain.findViewById<TextView>(R.id.position_textview)
            positionTextView.text = "Quantity: $position"
            return rowMain
        }

        override fun getItem(position: Int): Any {
            return "TESTING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return items.size
        }

    }


    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }


    override fun onSearchRequested(): Boolean {
        val appData = Bundle().apply {
            //putBoolean(JARGON, true)
        }
        startSearch(null, false, null, false)
        return true
    }
}
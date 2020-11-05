package com.mychef.mychef

import android.os.Bundle
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
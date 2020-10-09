package com.mychef.mychef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchFragment = SearchFragment()
        val filterFragment = FilterFragment()
        val ingredientFragment = IngredientFragment()

        setCurrentFragment(searchFragment)

        topNavigationView2.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miSearch -> setCurrentFragment(searchFragment)
                R.id.miFilter -> setCurrentFragment(filterFragment)
                R.id.miIngredient -> setCurrentFragment(ingredientFragment)
            }
            true
        }
    }


    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.searchFragment, fragment)
            commit()
        }
    }
}
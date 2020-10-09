package com.mychef.mychef

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        setCurrentFragment(favoritesFragment)

        bottomNavigationView2.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miFavorites -> setCurrentFragment(favoritesFragment)
                R.id.miPantry -> setCurrentFragment(pantryFragment)
                R.id.miList -> setCurrentFragment(listFragment)
                R.id.miMore -> setCurrentFragment(moreFragment)
            }
            true
        }

        val button = findViewById<Button>(R.id.button_search)
        button.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }


    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}
package com.mychef.mychef

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        title_text.text = intent.extras!!.getString("passtitle")!!
        time_text.text = intent.extras!!.getString("passtime")!!
        yield_text.text = intent.extras!!.getString("passyield")!!
        ingredient_text.text = intent.extras!!.getString("passingredients")!!

    }
}
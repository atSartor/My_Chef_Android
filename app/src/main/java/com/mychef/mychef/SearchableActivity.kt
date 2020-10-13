package com.mychef.mychef

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import android.util.JsonReader

class SearchableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
    }

    private fun doMySearch(query: String) {
        val textView = findViewById<TextView>(R.id.searchText)

        val queue = Volley.newRequestQueue(this)
        val url = "https://mychef-web.herokuapp.com/api/v1/show_recipes?search=$query"
        var i=1
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                    textView.text = "%s\n".format(response.getJSONObject(1).opt("title")) +
                            "\t\t\tPreparation Time: %s\n".format(response.getJSONObject(1).opt("total_time")) +
                            "\t\t\tServings: %s\n".format(response.getJSONObject(1).opt("yields"))
            },
            Response.ErrorListener { error ->
                textView.text = "That didn't work!"
            }
        )
        queue.add(jsonArrayRequest)
    }
}
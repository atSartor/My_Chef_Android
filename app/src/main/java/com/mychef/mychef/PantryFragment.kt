package com.mychef.mychef

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment

class PantryFragment: Fragment(R.layout.fragment_pantry) {
    val list: MutableList<String> = ArrayList()
    lateinit var adapter:ArrayAdapter<String>

    @Override
    protected fun onCreate(savedState:Bundle) {
        super.onCreate(savedState)
        setContentView(R.layout.fragment_pantry)
        val lView = findViewById(R.id.ListView) as ListView
        val itemText = findViewById(R.id.addText) as EditText
        val addButton = findViewById(R.id.addButton) as Button
        itemList = arrayListOf()
        adapter = ArrayAdapter<String>(this@PantryFragment, android.R.layout.simple_list_item_multiple_choice, itemList)
        val addlistener = object:View.OnClickListener() {
            fun onClick(v:View) {
                itemList.add(itemText.getText().toString())
                itemText.setText("")
                adapter.notifyDataSetChanged()
            }
        }
        addButton.setOnClickListener(addlistener)
        lView.setAdapter(adapter)
    }

}



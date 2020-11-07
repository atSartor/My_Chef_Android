package com.mychef.mychef

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.R

class PantryFragment: Fragment(R.layout.fragment_pantry) {
    private class CustomAdapter(context: Context): BaseAdapter {

        private val mContext: Context

        init {
            this.mContext = context
        }
        fun getCount(): Int {

        }
        fun getItemId(position: Int): Long{
            return position.toLong()
        }
        fun getItem(position: Int): Any{
            return "TESTING"
        }
        fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = "Test Row for ListView"
            return textView
        }
    }
}



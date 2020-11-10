package com.mychef.mychef

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_more.view.*

class MoreFragment: Fragment (R.layout.fragment_more) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view: View = inflater.inflate(R.layout.fragment_more, container, false)

        view.sign_out_button.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        // Return the fragment view/layout
        return view
    }

}


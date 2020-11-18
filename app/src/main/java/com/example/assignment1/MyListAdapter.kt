package com.example.assignment1

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity, private val title: ArrayList<String>, private val body:ArrayList<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater!!.inflate(R.layout.custom_list, null, true)

        val titleText = rowView.findViewById(R.id.label) as TextView
        val bodyText = rowView.findViewById(R.id.post) as TextView

        titleText.text = title[position]
        bodyText.text = body[position]

        return rowView
    }
}
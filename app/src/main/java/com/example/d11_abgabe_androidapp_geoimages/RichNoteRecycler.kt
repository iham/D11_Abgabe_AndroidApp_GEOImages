package com.example.d11_abgabe_androidapp_geoimages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RichNoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textV: TextView

    init {
        textV = itemView.findViewById<TextView>(R.id.test_text)
    }
}

class RichNoteAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<RichNoteViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RichNoteViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.test_item, viewGroup, false)

        return RichNoteViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: RichNoteViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textV.text = dataSet[position]
    }

    override fun getItemCount() = dataSet.size
}
package com.example.d11_abgabe_androidapp_geoimages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class RichNoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var liTitle: TextView
    var liText: TextView
    var liImage: ImageView
    var liLongitude: TextView
    var liLatitude: TextView


    init {
        liTitle = itemView.findViewById<TextView>(R.id.li_title)
        liText = itemView.findViewById<TextView>(R.id.li_text)
        liImage = itemView.findViewById<ImageView>(R.id.li_image)
        liLongitude = itemView.findViewById<TextView>(R.id.li_longitude)
        liLatitude = itemView.findViewById<TextView>(R.id.li_latitude)
    }

}

class RichNoteAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<RichNoteViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RichNoteViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return RichNoteViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: RichNoteViewHolder, position: Int) {
        viewHolder.liTitle.text = dataSet[position]
        viewHolder.liText.text = "empty"
        viewHolder.liLongitude.text = "0000.00000"
        viewHolder.liLatitude.text = "0000.00000"
        viewHolder.liImage.setImageResource(R.drawable.ic_action_format_list_bulleted)

        viewHolder.itemView.setOnClickListener {
            Toast.makeText(it.context, "clicked item $position", Toast.LENGTH_SHORT).show()
            (it.context as MainActivity).loadFragment(DetailFragment())
        }
    }

    override fun getItemCount() = dataSet.size
}
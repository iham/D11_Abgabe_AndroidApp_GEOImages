package com.example.d11_abgabe_androidapp_geoimages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RichNoteListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

class RichNoteListItemAdapter(private val dataSet: List<RichNote>) : RecyclerView.Adapter<RichNoteListItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RichNoteListItemViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return RichNoteListItemViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: RichNoteListItemViewHolder, position: Int) {
        viewHolder.liTitle.text = dataSet[position].title
        viewHolder.liText.text = dataSet[position].text
        viewHolder.liLongitude.text = dataSet[position].longitude.toString()
        viewHolder.liLatitude.text = dataSet[position].latitude.toString()
        viewHolder.liImage.setImageResource(R.drawable.ic_action_format_list_bulleted)

        viewHolder.itemView.setOnClickListener {
            (it.context as MainActivity).selectedRichNote = dataSet[position]
            (it.context as MainActivity).loadFragment(DetailFragment())
        }
    }

    override fun getItemCount() = dataSet.size
}

class RichNoteGridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    var giTitle: TextView
//    var giText: TextView
    var giImage: ImageView
//    var giLongitude: TextView
//    var giLatitude: TextView

    init {
//        giTitle = itemView.findViewById<TextView>(R.id.gi_title)
//        giText = itemView.findViewById<TextView>(R.id.gi_text)
        giImage = itemView.findViewById<ImageView>(R.id.gi_image)
//        giLongitude = itemView.findViewById<TextView>(R.id.gi_longitude)
//        giLatitude = itemView.findViewById<TextView>(R.id.gi_latitude)
    }

}
class RichNoteGridItemAdapter(private val dataSet: List<RichNote>) : RecyclerView.Adapter<RichNoteGridItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RichNoteGridItemViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.grid_item, viewGroup, false)

        return RichNoteGridItemViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: RichNoteGridItemViewHolder, position: Int) {
//        viewHolder.giTitle.text = dataSet[position]
//        viewHolder.giText.text = "empty"
//        viewHolder.giLongitude.text = "0000.00000"
//        viewHolder.giLatitude.text = "0000.00000"
        viewHolder.giImage.setImageResource(R.drawable.ic_action_format_list_bulleted)

        viewHolder.itemView.setOnClickListener {
            (it.context as MainActivity).selectedRichNote = dataSet[position]
            (it.context as MainActivity).loadFragment(DetailFragment())
        }
    }

    override fun getItemCount() = dataSet.size
}

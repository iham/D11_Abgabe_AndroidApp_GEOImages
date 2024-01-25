package com.example.d11_abgabe_androidapp_geoimages

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class DetailFragment : BaseFragment() {
    override val contentView = R.layout.fragment_detail
    override var hasBackButton: Boolean = true
    override var hasNavigation: Boolean = false

    override val menuItems = listOf(
        R.menu.edit,
        R.menu.remove,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Toast.makeText(context, "create detail note: ${(activity as MainActivity).selectedRichNoteID}", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        // fill data into fields
        val note = (activity as MainActivity).selectedRichNote
        if(note != null) {
            view?.findViewById<TextView>(R.id.d_title)?.text = note.title
            view?.findViewById<TextView>(R.id.d_longitude)?.text = note.longitude.toString()
            view?.findViewById<TextView>(R.id.d_latitude)?.text = note.latitude.toString()
            view?.findViewById<TextView>(R.id.d_text)?.text = note.text
//            if (note.image != "") {
//                view?.findViewById<ImageView>(R.id.d_image)?.setImageDrawable(note.image)
//            }

        }
    }
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            // add new RichNote
            R.id.edit -> {
                Toast.makeText(activity, "Editing", Toast.LENGTH_LONG).show()
                (activity as MainActivity).loadFragment(EditFragment())
                true
            }
            // remove
            R.id.remove -> {
                Toast.makeText(activity, "Delete", Toast.LENGTH_LONG).show()
                (activity as MainActivity).showDeleteDialog()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

}


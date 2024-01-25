package com.example.d11_abgabe_androidapp_geoimages

import android.os.Bundle
import android.view.MenuItem
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
        Toast.makeText(context, "resume detail", Toast.LENGTH_SHORT).show()
        // fill data into fields
        if((activity as MainActivity).selectedRichNote != null) {
//            find
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


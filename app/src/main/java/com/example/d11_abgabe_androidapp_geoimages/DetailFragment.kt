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
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            // add new RichNote
            R.id.edit -> {
                Toast.makeText(activity, "Add & Editing", Toast.LENGTH_LONG).show()
                (activity as MainActivity).loadFragment(EditFragment())
                true
            }
            // remove
            R.id.remove -> {
                Toast.makeText(activity, "Delete", Toast.LENGTH_LONG).show()
                (activity as MainActivity).showDeleteDialog()
                // TODO change sort order in listing
                true
            }
            R.id.title_desc -> {
                Toast.makeText(activity, "sort title DESC", Toast.LENGTH_LONG).show()
                // TODO change sort order in listing
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

}


package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import android.view.MenuItem
import android.widget.Toast

class ListingFragment : BaseFragment() {
    override val contentView = R.layout.fragment_listing
    override val menuItems = listOf(
        R.menu.sort,
        R.menu.add,
    )

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.title_asc -> {
                Toast.makeText(activity, "sort title ASC", Toast.LENGTH_LONG).show()
                true
            }
            R.id.title_desc -> {
                Toast.makeText(activity, "sort title DESC", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }
}


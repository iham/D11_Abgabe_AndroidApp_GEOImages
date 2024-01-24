package com.example.d11_abgabe_androidapp_geoimages

import android.view.MenuItem

class MapFragment : BaseFragment() {
    override val contentView = R.layout.fragment_map

    override val menuItems = listOf(
        R.menu.add,
        R.menu.samples,
    )

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            else -> super.onOptionsItemSelected(menuItem)
        }
    }
}


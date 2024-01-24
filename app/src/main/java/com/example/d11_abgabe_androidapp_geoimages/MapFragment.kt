package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle

class MapFragment : BaseFragment() {
    override val contentView = R.layout.fragment_map

    override val menuItems = listOf(
        R.menu.add,
    )

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            else -> super.onOptionsItemSelected(menuItem)
        }
    }
}


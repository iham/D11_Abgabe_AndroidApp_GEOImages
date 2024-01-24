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

class GridFragment : BaseFragment() {
    override val contentView = R.layout.fragment_grid
    override val menuItems = listOf(
        R.menu.sort,
        R.menu.add,
    )

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            // sorting
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


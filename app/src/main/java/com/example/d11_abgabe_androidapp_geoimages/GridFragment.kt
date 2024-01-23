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

class GridFragment : Fragment(R.layout.fragment_grid), MenuProvider {
    var menuItems = listOf(
        R.menu.sort,
        R.menu.add,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        // Add menu items here
        menuItems.forEach {
            menuInflater.inflate(it, menu)
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Handle the menu selection
        return when (menuItem.itemId) {
            R.id.add -> {
                Toast.makeText(activity, "testst", Toast.LENGTH_LONG).show()
                startActivity(Intent(context, DetailActivity::class.java))
                true
            }
            else -> false
        }
    }
}


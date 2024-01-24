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

class DetailFragment : BaseFragment() {
    override val contentView = R.layout.fragment_edit
    override var hasBackButton: Boolean = true
    override var hasNavigation: Boolean = false

    override val menuItems = listOf(
        R.menu.edit,
        R.menu.remove
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
                // TODO implement alert dialog and deleting
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


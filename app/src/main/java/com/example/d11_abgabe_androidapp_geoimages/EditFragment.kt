package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView

class EditFragment : BaseFragment() {
    override val contentView = R.layout.fragment_edit

    override val menuItems = listOf(
        R.menu.save,
    )
    override var hasNavigation: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enable back button
        (activity as MainActivity).enableHomeButton()
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            // add new RichNote
            R.id.save -> {
                Toast.makeText(activity, "Saving", Toast.LENGTH_LONG).show()
                true
            }
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


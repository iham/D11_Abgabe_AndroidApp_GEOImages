package com.example.d11_abgabe_androidapp_geoimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseFragment : Fragment(), MenuProvider {
    abstract val contentView: Int
    abstract val menuItems: List<Int>
    open var hasNavigation: Boolean = true
    open var hasBackButton: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        // enable back button
        if (hasBackButton) (activity as MainActivity).enableHomeButton() else (activity as MainActivity).disableHomeButton()

        // de|activate nav
        val nav = activity?.findViewById<BottomNavigationView>(R.id.navigation)
        nav?.visibility = if (hasNavigation) View.VISIBLE else View.GONE

    }

    // setting Fragment View Template
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(contentView, container, false)
        return view
    }

    // control the menu from within the Fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    // inject menu items as defined in menuItems
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        // Add menu items here
        menu.clear()
        menuItems.forEach {
            menuInflater.inflate(it, menu)
        }
    }

    // the menu selection actions should be defined in each Fragment
    abstract override fun onMenuItemSelected(menuItem: MenuItem): Boolean
}


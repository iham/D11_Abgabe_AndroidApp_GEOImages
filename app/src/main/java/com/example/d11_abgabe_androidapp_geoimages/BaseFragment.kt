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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseFragment : Fragment(), MenuProvider {
    abstract val contentView: Int
    abstract val menuItems: List<Int>
    open var hasNavigation: Boolean = true
    open var hasBackButton: Boolean = false

    val dataset = arrayOf(
        "test bla",
        "test 2",
        "test 3",
        "test 4",
        "test 5",
        "test 6",
        "test 7",
        "test 8",
        "test 9",
        "test 10",
        "test 11",
        "test 12",
    )
    val customAdapter = RichNoteAdapter(dataset)
    var recyclerView: RecyclerView? = null
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

        // data connection
        recyclerView = activity?.findViewById<RecyclerView>(R.id.listing)
        recyclerView?.hasFixedSize()
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = RichNoteAdapter(dataset)

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


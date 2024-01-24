package com.example.d11_abgabe_androidapp_geoimages

import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class GridFragment : BaseFragment() {
    override val contentView = R.layout.fragment_grid
    override val menuItems = listOf(
        R.menu.sort,
        R.menu.add,
        R.menu.samples,
    )

    override fun onResume() {
        super.onResume()
        // data connection
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.grid_list)
        recyclerView?.hasFixedSize()
        recyclerView?.layoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL)
        recyclerView?.adapter = RichNoteGridItemAdapter((activity as MainActivity).dataset)
    }
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


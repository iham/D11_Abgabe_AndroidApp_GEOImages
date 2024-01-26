package com.example.d11_abgabe_androidapp_geoimages

import android.annotation.SuppressLint
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListingFragment : BaseFragment() {
    override val contentView = R.layout.fragment_listing
    override val menuItems = listOf(
        R.menu.sort,
        R.menu.add,
        R.menu.samples,
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        // data connection
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.listing_list)
        recyclerView?.hasFixedSize()
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = RichNoteListItemAdapter((activity as MainActivity).richNotes)
        recyclerView?.adapter?.notifyDataSetChanged()
    }
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.title_asc -> {
                Toast.makeText(activity, getString(R.string.sort_title_asc), Toast.LENGTH_LONG).show()
                true
            }
            R.id.title_desc -> {
                Toast.makeText(activity, getString(R.string.sort_title_desc), Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }
}


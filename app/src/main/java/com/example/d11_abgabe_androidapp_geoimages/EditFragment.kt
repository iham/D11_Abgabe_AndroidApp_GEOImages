package com.example.d11_abgabe_androidapp_geoimages

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast

class EditFragment : BaseFragment() {
    override val contentView = R.layout.fragment_edit
    override var hasBackButton: Boolean = true
    override var hasNavigation: Boolean = false

    override val menuItems = listOf(
        R.menu.save,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enable back button
        (activity as MainActivity).enableHomeButton()
        // grab item
        Toast.makeText(context, "create edit/add note: ${(activity as MainActivity).selectedRichNote}", Toast.LENGTH_SHORT).show()

    }

//    override fun onResume() {
//        super.onResume()
//        Toast.makeText(context, "resume edit/add", Toast.LENGTH_SHORT).show()
//
//    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            // add new RichNote
            R.id.save -> {
                Toast.makeText(activity, "Saving", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }
}


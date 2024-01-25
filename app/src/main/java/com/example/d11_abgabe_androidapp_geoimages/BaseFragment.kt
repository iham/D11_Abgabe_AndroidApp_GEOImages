package com.example.d11_abgabe_androidapp_geoimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
//    var selectedRichNote: RichNote? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).richNotes = (activity as MainActivity).richNoteDao.getAll()
//        if ((activity as MainActivity).selectedRichNote != null) {
//            Toas
//        }
    }

    override fun onResume() {
        super.onResume()
        // enable back button
        if (hasBackButton) (activity as MainActivity).enableHomeButton() else (activity as MainActivity).disableHomeButton()

        // de | activate nav
        val nav = activity?.findViewById<BottomNavigationView>(R.id.navigation)
        nav?.visibility = if (hasNavigation) View.VISIBLE else View.GONE

        // update data for recycler views
        (activity as MainActivity).richNotes = (activity as MainActivity).richNoteDao.getAll()
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
        // Reset Menu
        menu.clear()
        // add menu items based on defined list
        menuItems.forEach {
            menuInflater.inflate(it, menu)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add -> {
                Toast.makeText(context, "Add & Edit", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).selectedRichNote = null
                (activity as MainActivity).loadFragment(EditFragment())
                true
            }
            R.id.delete_samples -> {
                (activity as MainActivity).deleteSampleData(10)
                onResume()
                true
            }
            R.id.delete_all_samples -> {
                (activity as MainActivity).deleteSampleData(-1)
                onResume()
                true
            }
            R.id.add_samples -> {
                (activity as MainActivity).createSampleData(10)
                onResume()
                true
            }
            R.id.add_one_samples -> {
                (activity as MainActivity).createSampleData(1)
                onResume()
                true
            }
            android.R.id.home -> {
                Toast.makeText(context, "Back", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).selectedRichNote = null
                activity?.supportFragmentManager?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


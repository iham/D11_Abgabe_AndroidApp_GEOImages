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
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

abstract class BaseFragment : Fragment(), MenuProvider {
    abstract val contentView: Int
    abstract val menuItems: List<Int>
    open var hasNavigation: Boolean = true
    open var hasBackButton: Boolean = false

    lateinit var richNotesDB: RichNotesDB
    lateinit var richNoteDao: RichNoteDao
    lateinit var richNotes: List<RichNote>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // db
        richNotesDB = RichNotesDB.getInstance(requireActivity())
    }

    override fun onResume() {
        Toast.makeText(activity, "resume fragment", Toast.LENGTH_SHORT).show()
        super.onResume()
        // enable back button
        if (hasBackButton) (activity as MainActivity).enableHomeButton() else (activity as MainActivity).disableHomeButton()

        // de|activate nav
        val nav = activity?.findViewById<BottomNavigationView>(R.id.navigation)
        nav?.visibility = if (hasNavigation) View.VISIBLE else View.GONE

        // notes
        richNoteDao = richNotesDB.richNoteDao
        richNotes = richNoteDao.getAll()
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

    fun recreate() {
        val ft: FragmentTransaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        ft.detach(this)
        ft.attach(this)
        ft.commit()
    }
    // the menu selection actions should be defined in each Fragment
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add -> {
                Toast.makeText(context, "Add & Edit", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).loadFragment(EditFragment())
                true
            }
            R.id.delete_samples -> {
                deleteSampleData(10)
                onResume()
                true
            }
            R.id.delete_all_samples -> {
                deleteSampleData(-1)
                onResume()
                true
            }
            R.id.add_samples -> {
                createSampleData(10)
                onResume()
                true
            }
            R.id.add_one_samples -> {
                createSampleData(1)
                onResume()
                true
            }
            android.R.id.home -> {
                Toast.makeText(context, "Back", Toast.LENGTH_SHORT).show()
                activity?.supportFragmentManager?.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createSampleData(amount: Int = 10) {
        var latLon = LatLng(0.0, 0.0)
        val lastKnownLocation = (activity as MainActivity).lastKnownLocation
        if(lastKnownLocation != null) {
            latLon = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
        }
        val sampleLocation = generateRandomLocationInsideRadius(latLon.latitude, latLon.longitude, 1000)
        repeat (amount) {
            val richNote = RichNote(
                "Title $it",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod",
                "",
                sampleLocation.longitude,
                sampleLocation.latitude,
            )
            richNoteDao.insertAll(richNote)
        }
        richNotes = richNoteDao.getAll()
        Toast.makeText(context, "$amount Samples created", Toast.LENGTH_SHORT).show()
    }

    private fun deleteSampleData(amount: Int = 10) {
        var i = amount
        if (i == -1) {
            i = richNotes.size
        }
        repeat(i) {
            richNoteDao.delete(richNotes[it])
        }
        richNotes = richNoteDao.getAll()
        Toast.makeText(context, "$i Samples deleted", Toast.LENGTH_SHORT).show()
    }

    private fun generateRandomLocationInsideRadius(x0: Double, y0: Double, radius: Int): LatLng {
        val random = Random.Default
        // Convert radius from meters to degrees
        val radiusInDegrees = (radius / 111000f).toDouble()
        val u = random.nextDouble()
        val v = random.nextDouble()
        val w = radiusInDegrees * sqrt(u)
        val t = 2.0 * Math.PI * v
        val x = w * cos(t)
        val y = w * sin(t)
        // Adjust the x-coordinate for the shrinking of the east-west distances
        val newX = x / cos(Math.toRadians(y0))
        val foundLongitude = newX + x0
        val foundLatitude = y + y0
        return LatLng(foundLongitude, foundLatitude)
    }

}


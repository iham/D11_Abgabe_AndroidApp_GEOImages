package com.example.d11_abgabe_androidapp_geoimages

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(R.layout.activity_main) {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // enable toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_arrow_back)

        // load the content to fragment container
        loadFragment(ListingFragment())

        // navigation
        val nav = findViewById<BottomNavigationView>(R.id.navigation)
        nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.list -> loadFragment(ListingFragment())
                R.id.grid -> loadFragment(GridFragment())
                R.id.map -> loadFragment(MapFragment())
            }
            true
        }

        // TODO get rich notes
        // set listeners for navigation
        // Toast.makeText(this, "richNotes in DB: ${richNotes.size}", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add -> {
                Toast.makeText(this, "Add & Edit", Toast.LENGTH_LONG).show()
                loadFragment(EditFragment())
                true
            }
            android.R.id.home -> {
                Toast.makeText(this, "Back", Toast.LENGTH_LONG).show()
                supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
    fun enableHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    fun disableHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

    }
    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .add(fragment, fragment.tag)
            .addToBackStack(fragment.tag)
            .replace(R.id.container,fragment)
            .commit()
    }

}
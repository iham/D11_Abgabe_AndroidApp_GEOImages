package com.example.d11_abgabe_androidapp_geoimages

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    lateinit var richNotesDB: RichNotesDB
    lateinit var richNoteDao: RichNoteDao
    lateinit var richNotes: List<RichNote>


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var lastKnownLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // enable toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_arrow_back)

        // load the content to fragment container
        loadFragment(ListingFragment())
//        loadFragment(GridFragment())

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

        // getting richNotes
        richNotesDB = RichNotesDB.getInstance(this)
        richNoteDao = richNotesDB.richNoteDao
        richNotes = richNoteDao.getAll()


        // location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            // Got last known location. In some rare situations this can be null.
            if(location != null) {
                lastKnownLocation = location
            }

        }
    }

    override fun onResume() {
        super.onResume()
        richNotes = richNoteDao.getAll()
    }


    fun enableHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    fun disableHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun showDeleteDialog() {
        AlertDialog.Builder(this)
            .setMessage("Do you want to remove this note?")
            .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                // TODO IHAM delete from DB
                // and move back to last page as item doesn't exist anymore
                supportFragmentManager.popBackStack()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, which ->
                Toast.makeText(this, "Abort", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .add(fragment, fragment.tag)
            .addToBackStack(fragment.tag)
            .replace(R.id.container, fragment)
            .commit()
    }

    fun createSampleData(amount: Int = 10) {
        var latLon = LatLng(0.0, 0.0)
        val lastKnownLocation = lastKnownLocation
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
        Toast.makeText(this, "$amount Samples created", Toast.LENGTH_SHORT).show()
    }

    fun deleteSampleData(amount: Int = 10) {
        var i = amount
        if (i == -1) {
            i = richNotes.size
        }
        repeat(i) {
            richNoteDao.delete(richNotes[it])
        }
        Toast.makeText(this, "$i Samples deleted", Toast.LENGTH_SHORT).show()
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
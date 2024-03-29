package com.example.d11_abgabe_androidapp_geoimages

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random


class MainActivity : AppCompatActivity(R.layout.activity_main), LocationListener {
    lateinit var richNotesDB: RichNotesDB
    lateinit var richNoteDao: RichNoteDao
    lateinit var richNotes: List<RichNote>
    var selectedRichNote: RichNote? = null

    private val locationPermissionCode = 2
    private lateinit var locationManager: LocationManager

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var lastKnownLocation: Location? = null

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

        // getting richNotes
        richNotesDB = RichNotesDB.getInstance(this)
        richNoteDao = richNotesDB.richNoteDao
        richNotes = richNoteDao.getAll()

        getLocation()
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }
    override fun onLocationChanged(location: Location) {
        lastKnownLocation = location
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
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
            .setMessage(getString(R.string.delete_message))
            .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                Toast.makeText(this, getString(R.string.note_deleted), Toast.LENGTH_SHORT).show()
                richNoteDao.delete(selectedRichNote!!)

                // and move back to last page as item doesn't exist anymore
                selectedRichNote = null
                supportFragmentManager.popBackStack()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, which ->
                Toast.makeText(this, getString(R.string.abort), Toast.LENGTH_SHORT).show()
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
        val latitude = lastKnownLocation?.latitude?: 0.0
        val longitude = lastKnownLocation?.longitude?: 0.0
        val sampleLocation = generateRandomLocationInsideRadius(latitude, longitude, 1000)
        repeat (amount) {
            val richNote = RichNote(
                "Title $it",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod",
                IMAGE_URIS[Random.nextInt(IMAGE_URIS.size)],
                sampleLocation.longitude,
                sampleLocation.latitude,
            )
            richNoteDao.insertAll(richNote)
        }

        Toast.makeText(this, "$amount ${getString(R.string.samples_created)}", Toast.LENGTH_SHORT).show()
    }

    fun deleteSampleData(amount: Int = 10) {
        var i = amount
        if (i == -1 || i > richNotes.size) {
            i = richNotes.size
        }
        repeat(i) {
            richNoteDao.delete(richNotes[it])
        }
        Toast.makeText(this, "$i ${getString(R.string.samples_deleted)}", Toast.LENGTH_SHORT).show()
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
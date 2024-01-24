package com.example.d11_abgabe_androidapp_geoimages

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    // Using https://kotlinlang.org/docs/delegated-properties.html#lazy-properties
    val richNoteDao: RichNoteDao by lazy {
        RichNotesDB.getInstance(this).richNoteDao
    }
    val richNotes: List<RichNote> by lazy {
        richNoteDao.getAll()
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add -> {
                Toast.makeText(this, "Add & Edit", Toast.LENGTH_LONG).show()
                loadFragment(EditFragment())
                true
            }
            R.id.samples -> {
                Toast.makeText(this, "Samples", Toast.LENGTH_LONG).show()
                // TODO create Samples
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
            .replace(R.id.container,fragment)
            .commit()
    }

}
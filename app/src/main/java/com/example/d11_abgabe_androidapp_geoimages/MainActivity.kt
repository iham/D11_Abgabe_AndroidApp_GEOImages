package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
            .add(fragment, fragment.tag)
            .addToBackStack(fragment.tag)
            .replace(R.id.container,fragment)
            .commit()
    }

}
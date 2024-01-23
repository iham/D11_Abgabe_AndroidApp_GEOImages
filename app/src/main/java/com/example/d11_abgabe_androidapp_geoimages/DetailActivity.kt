package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.core.view.iterator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class DetailActivity : BaseActivity() {

    override val contentView = R.layout.activity_detail
    override var menuItems = listOf(
        R.menu.edit,
        R.menu.remove,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_arrow_back)

        val nav = findViewById<BottomNavigationView>(R.id.navigation)
        nav.visibility = View.GONE

        // TODO get rich notes

        // set listeners for navigation
        // Toast.makeText(this, "richNotes in DB: ${richNotes.size}", Toast.LENGTH_SHORT).show()
    }
}
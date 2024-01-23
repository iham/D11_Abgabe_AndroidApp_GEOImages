package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseActivity : AppCompatActivity() {

    // Using https://kotlinlang.org/docs/delegated-properties.html#lazy-properties
    private val richNoteDao: RichNoteDao by lazy {
        RichNotesDB.getInstance(this).richNoteDao
    }
    val richNotes: List<RichNote> by lazy {
        richNoteDao.getAll()
    }

    abstract val contentView: Int
    abstract val toolbarID: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)

        setSupportActionBar(findViewById(toolbarID))

        // set listeners for navigation
        findViewById<BottomNavigationView>(R.id.navigation).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.list -> startActivity(Intent(this, ListActivity::class.java))
                R.id.grid -> startActivity(Intent(this, GridActivity::class.java))
                R.id.map -> startActivity(Intent(this, MapActivity::class.java))
            }
            true
        }
    }
}
package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    abstract var menuItems: List<Int>
    abstract val contentView: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu items; this adds items to the action bar if it is present.
        menuItems.forEach {
            menuItem -> menuInflater.inflate(menuItem, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
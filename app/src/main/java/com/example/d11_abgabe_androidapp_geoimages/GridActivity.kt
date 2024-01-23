package com.example.d11_abgabe_androidapp_geoimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast

class GridActivity : BaseActivity() {

    // Using https://kotlinlang.org/docs/delegated-properties.html#lazy-properties
    private val richNoteDao: RichNoteDao by lazy {
        RichNotesDB.getInstance(this).richNoteDao
    }
    override val contentView = R.layout.activity_grid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO get rich notes
        val richNotes = richNoteDao.getAll()
        Toast.makeText(this, "richNotes in DB: ${richNotes.size}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.sort, menu)
        menuInflater.inflate(R.menu.add, menu)
        return true
    }
}
package com.example.d11_abgabe_androidapp_geoimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ListActivity : AppCompatActivity() {

    // Using https://kotlinlang.org/docs/delegated-properties.html#lazy-properties
    private val richNoteDao: RichNoteDao by lazy {
        RichNotesDB.getInstance(this).richNoteDao
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // TODO get rich notes
        val richNotes = richNoteDao.getAll()
        Toast.makeText(this, "richNotes in DB: ${richNotes.size}", Toast.LENGTH_SHORT).show()
    }
}
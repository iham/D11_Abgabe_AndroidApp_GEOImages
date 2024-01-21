package com.example.d11_abgabe_androidapp_geoimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ListActivity : AppCompatActivity() {

    private val richNotesDB: RichNotesDB by lazy {
        RichNotesDB.getInstance(this)
    }
    private val richNoteDao: RichNoteDao by lazy {
        richNotesDB.richNoteDao
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // TODO get rich notes
        val richNotes = richNoteDao.getAll()
        Toast.makeText(this, "richNotes in DB: ${richNotes.size}", Toast.LENGTH_SHORT).show()
    }
}
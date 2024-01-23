package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.navigation.NavigationBarView

class EditActivity : BaseActivity() {

    override val contentView = R.layout.activity_edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO get rich notes

        // set listeners for navigation
        // Toast.makeText(this, "richNotes in DB: ${richNotes.size}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.save, menu)
        return true
    }
}
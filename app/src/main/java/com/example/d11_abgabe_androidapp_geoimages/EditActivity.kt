package com.example.d11_abgabe_androidapp_geoimages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.core.view.iterator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class EditActivity : BaseActivity() {

    override val contentView = R.layout.activity_edit
    override var menuItems = listOf(
        R.menu.save,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nav = findViewById<BottomNavigationView>(R.id.navigation)
        // deactivate all menuitems (useful in detail & edit|add
        nav?.menu?.let {
            for (menuItem in it.iterator()) {
                menuItem.isCheckable = false
                menuItem.isChecked = false
            }
        }

        // TODO get rich notes
        // Toast.makeText(this, "richNotes in DB: ${richNotes.size}", Toast.LENGTH_SHORT).show()
    }
}
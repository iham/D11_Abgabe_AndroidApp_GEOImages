package com.example.d11_abgabe_androidapp_geoimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast

class GridActivity : BaseActivity() {

    override val contentView = R.layout.activity_grid
    override var menuItems = listOf(
        R.menu.sort,
        R.menu.add,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO get rich notes
        // Toast.makeText(this, "richNotes in DB: ${richNotes.size}", Toast.LENGTH_SHORT).show()
    }
}
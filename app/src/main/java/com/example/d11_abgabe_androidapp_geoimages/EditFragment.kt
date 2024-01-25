package com.example.d11_abgabe_androidapp_geoimages

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

class EditFragment : BaseFragment() {
    override val contentView = R.layout.fragment_edit
    override var hasBackButton: Boolean = true
    override var hasNavigation: Boolean = false

    override val menuItems = listOf(
        R.menu.save,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enable back button
        (activity as MainActivity).enableHomeButton()
    }

    override fun onResume() {
        super.onResume()
        val note = (activity as MainActivity).selectedRichNote
        view?.findViewById<EditText>(R.id.e_title)?.setText(note?.title)
        view?.findViewById<EditText>(R.id.e_longitude)?.setText(
            note?.longitude?.toString() ?: "0.0"
        )
        view?.findViewById<EditText>(R.id.e_latitude)?.setText(
            note?.latitude?.toString() ?: "0.0"
        )
        view?.findViewById<EditText>(R.id.e_text)?.setText(note?.text)
        Picasso.get()
            .load(note?.image)
            .placeholder(R.drawable.ic_action_image_search)
            .into(view?.findViewById<ImageView>(R.id.e_image))

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            // add | update RichNote
            R.id.save -> {
                val title = view?.findViewById<EditText>(R.id.e_title)?.text.toString()
                val text = view?.findViewById<EditText>(R.id.e_text)?.text.toString()
                val image = ""
                val longitude = view?.findViewById<EditText>(R.id.e_longitude)?.text.toString().toDouble()
                val latitude = view?.findViewById<EditText>(R.id.e_latitude)?.text.toString().toDouble()

                var richNote = (activity as MainActivity).selectedRichNote
                if (richNote != null) {
                    // update
                    richNote.title = title
                    richNote.text = text
                    richNote.image = image
                    richNote.longitude = longitude
                    richNote.latitude = latitude
                    (activity as MainActivity).richNoteDao.update(richNote)
                }
                else {
                    // insert new
                    val new = RichNote(title, text, image, longitude, latitude)
                    (activity as MainActivity).richNoteDao.insertAll(new)
                    (activity as MainActivity).selectedRichNote = new
                }

                Toast.makeText(
                    activity,
                    if (richNote != null) "Updated RichNote" else "Created RichNote",
                    Toast.LENGTH_SHORT
                ).show()

                activity?.supportFragmentManager?.popBackStack()
                onResume()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }
}


package com.example.d11_abgabe_androidapp_geoimages

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update

val IMAGE_URIS =  listOf(
    "https://i.imgur.com/1yRc8rL.jpeg",
    "https://i.imgur.com/8yzreIR.jpeg",
    "https://i.imgur.com/i1VhiuX.jpeg",
    "https://i.imgur.com/jrfqvzD.jpeg",
    "https://i.imgur.com/5QIU2J1.jpeg",
    "https://i.imgur.com/UwRQQvw.jpeg",
    "https://i.imgur.com/P11s7Qi.jpeg",
    "https://i.imgur.com/iLA75bA.jpeg",
    "https://i.imgur.com/id7mAhR.jpeg",
    "https://i.imgur.com/fgUXGBu.jpeg",
    "https://i.imgur.com/W5m0n6a.jpeg",
    "https://i.imgur.com/SnkZah7.jpeg",
    "https://i.imgur.com/4svTnPf.jpeg",
    "https://i.imgur.com/gvqaZrW.jpeg",
    "https://i.imgur.com/70V0dhK.jpeg",
)

@Entity(tableName = "rich_notes")
data class RichNote (
    var title: String,
    var text: String = "",
    var image: String = "",
    var longitude: Double = 0.0,
    var latitude: Double = 0.0,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)

@Dao
interface RichNoteDao {

    @Insert
    fun insertAll(vararg richNotes:RichNote)

    @Update
    fun update(richNote: RichNote)

    @Delete
    fun delete(richNote: RichNote)

    @Query("SELECT * from rich_notes")
    fun getAll(): List<RichNote>

    @Query("SELECT * FROM rich_notes WHERE id is :id")
    fun getAllById(id: Int): List<RichNote>

    @Query("SELECT * FROM rich_notes ORDER BY CASE WHEN :isASC = 1 THEN :field END ASC, CASE WHEN :isASC = 0 THEN :field END DESC")
    fun getAllSorted(field: String = "title", isASC: Boolean = true): List<RichNote>

}

@Database(entities = [RichNote::class], version = 1)
abstract class RichNotesDB: RoomDatabase() {
    abstract val richNoteDao: RichNoteDao

    // make DB a Singleton to only create it once and use as often as needed
    companion object {
        private var INSTANCE: RichNotesDB? = null

        fun getInstance(context: Context): RichNotesDB {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RichNotesDB::class.java,
                    "richnote_db"
                ).allowMainThreadQueries().build()
            }

            return INSTANCE as RichNotesDB
        }
    }
}
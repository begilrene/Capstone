package com.example.capstone.Dao

import androidx.room.*
import com.example.capstone.Entities.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getAllNotes(): List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)
}
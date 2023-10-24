package com.kepler.paginationkotlin.hilt_fundamental.db

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kepler.paginationkotlin.hilt_fundamental.utils.Constants.NOTE_TABLE


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity)

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE ORDER by noteID")
    fun getAllNote() : MutableList<NoteEntity>

    @Query("SELECT * FROM $NOTE_TABLE WHERE noteID like :id")
    fun getNote(id:Int) : NoteEntity
}
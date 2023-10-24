package com.kepler.paginationkotlin.hilt_fundamental.repository

import com.kepler.paginationkotlin.hilt_fundamental.db.NoteDao
import com.kepler.paginationkotlin.hilt_fundamental.db.NoteEntity
import javax.inject.Inject

class DbRepository @Inject constructor(
    private val dao: NoteDao
) {
    fun saveNote(noteEntity: NoteEntity) = dao.insertNote(noteEntity)
    fun updateNote(noteEntity: NoteEntity) = dao.updateNote(noteEntity)
    fun deleteNote(noteEntity: NoteEntity) = dao.deleteNote(noteEntity)
    fun getNote(id: Int) = dao.getNote(id)
    fun getAllNote() = dao.getAllNote()
}
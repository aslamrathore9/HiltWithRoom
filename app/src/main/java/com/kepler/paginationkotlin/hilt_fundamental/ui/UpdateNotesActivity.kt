package com.kepler.paginationkotlin.hilt_fundamental.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kepler.paginationkotlin.R
import com.kepler.paginationkotlin.databinding.ActivityUpdateNoteBinding
import com.kepler.paginationkotlin.hilt_fundamental.db.NoteEntity
import com.kepler.paginationkotlin.hilt_fundamental.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding

    @Inject
    lateinit var dbRepository: DbRepository

    @Inject
    lateinit var noteEntity: NoteEntity

    private var noteID = 0
    private var defaultTitle = ""
    private var defaultDesc = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            noteID = it.getInt("NOTE_ID")
        }

        binding.apply {

            val currentNote = dbRepository.getNote(noteID)
            defaultTitle = currentNote.noteTitle
            defaultDesc = currentNote.noteDesc

            edtTitle.setText(defaultTitle)
            edtDesc.setText(defaultDesc)

            binding.btnDelete.setOnClickListener {
                noteEntity = NoteEntity(noteID, defaultTitle, defaultDesc)
                dbRepository.deleteNote(noteEntity)
                finish()
            }


            binding.btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    noteEntity = NoteEntity(noteID, title, desc)
                    dbRepository.saveNote(noteEntity)
                    finish()
                } else {
                    Toast.makeText(
                        this@UpdateNotesActivity,
                        "Please add Title and Description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }

    }
}
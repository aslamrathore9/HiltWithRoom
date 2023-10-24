package com.kepler.paginationkotlin.hilt_fundamental.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.kepler.paginationkotlin.R
import com.kepler.paginationkotlin.databinding.ActivityAddNoteBinding
import com.kepler.paginationkotlin.hilt_fundamental.db.NoteEntity
import com.kepler.paginationkotlin.hilt_fundamental.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var entity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnAddNote.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    entity = NoteEntity(0, title, desc)
                    repository.saveNote(entity)
                    finish()
                } else {
                    Toast.makeText(
                        this@AddNotesActivity,
                        "Please add Title and Description",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }


    }
}
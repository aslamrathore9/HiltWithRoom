package com.kepler.paginationkotlin.hilt_fundamental.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kepler.paginationkotlin.R
import com.kepler.paginationkotlin.databinding.ActivityMainBinding
import com.kepler.paginationkotlin.hilt_fundamental.adapter.NoteAdapter
import com.kepler.paginationkotlin.hilt_fundamental.db.NoteEntity
import com.kepler.paginationkotlin.hilt_fundamental.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteAdapter: NoteAdapter

    @Inject
    lateinit var entity: NoteEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
        }
    }


    override fun onResume() {
        super.onResume()
        checkItem()
    }

    fun checkItem() {
        binding.apply {
            if (repository.getAllNote().isNotEmpty()) {
                binding.rvNoteList.visibility = View.VISIBLE
                binding.tvEmptyText.visibility = View.GONE
                noteAdapter.differ.submitList(repository.getAllNote())
                setUpRecyclerView()
            } else {
                binding.rvNoteList.visibility = View.GONE
                binding.tvEmptyText.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvNoteList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteAdapter
        }
    }

}
package com.raihanarman.testroom.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.raihanarman.testroom.R
import com.raihanarman.testroom.adapter.NoteAdapter
import com.raihanarman.testroom.databinding.FragmentListNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list_note.*
import javax.inject.Inject

@AndroidEntryPoint
class ListNoteFragment : Fragment() {

    val noteAdapter: NoteAdapter = NoteAdapter()
    lateinit var viewModel: NoteViewModel
    private var _binding : FragmentListNoteBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        subscribeToObservers()
        setupRecyclerView()

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(ListNoteFragmentDirections.actionListNoteFragmentToAddNoteFragment())
        }
    }

    private fun subscribeToObservers(){
        viewModel?.noteList?.observe(viewLifecycleOwner, Observer {
            noteAdapter.noteList = it
        })
    }

    private fun setupRecyclerView(){
        binding.rvNote.apply {
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
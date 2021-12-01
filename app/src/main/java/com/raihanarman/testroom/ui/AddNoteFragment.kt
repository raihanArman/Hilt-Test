package com.raihanarman.testroom.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.androiddevs.shoppinglisttestingyt.other.Status
import com.google.android.material.snackbar.Snackbar
import com.raihanarman.testroom.R
import com.raihanarman.testroom.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_note.*

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding ?= null
    private val binding get() = _binding!!

    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)
        subscribeToObserves()
        binding.btnAdd.setOnClickListener {
            viewModel.insertNote(
                et_nama.text.toString(),
                et_umur.text.toString()
            )
        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }

    private fun subscribeToObserves() {
        viewModel.insertNoteStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result->
                when(result.status){
                    Status.SUCCESS ->{
                        Toast.makeText(requireActivity(), "Berhasil tambah note", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    Status.ERROR ->{
                        Toast.makeText(requireActivity(), "Gagal tambah note", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
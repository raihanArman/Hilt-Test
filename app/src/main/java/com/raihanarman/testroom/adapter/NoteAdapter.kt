package com.raihanarman.testroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raihanarman.testroom.R
import com.raihanarman.testroom.data.local.Note
import com.raihanarman.testroom.databinding.ItemNoteBinding

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val diffCalback = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCalback)

    var noteList: List<Note>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemNoteBinding = DataBindingUtil.inflate(inflater, R.layout.item_note, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = noteList.size

    inner class ViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note){
            binding.tvNama.text = note.nama
            binding.tvUmur.text = "${note.umur}"
        }
    }


}
package com.example.m7l1.presentation.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m7l1.databinding.ItemNoteBinding
import com.example.m7l1.domain.model.Note

class NotesAdapter(
    private val onItemClickListener: (Note) -> Unit,
    private val onLongItemClickListener: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var list = mutableListOf<Note>()
    private var deleteNote: Note? = null

    fun updateList(notes: MutableList<Note>) {
        list = notes
        notifyDataSetChanged()
    }

//    fun deleteItem(position: Int) {
//        list.removeAt(position)
//        notifyItemRemoved(position)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNoteBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun delete(){
        if (deleteNote!=null){
            list.remove(deleteNote)
            deleteNote = null
            notifyDataSetChanged()
        }
    }


    inner class NotesViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: Note) {
            with(binding) {
                title.text = note.title
                description.text = note.description
                time.text = note.createAt.toString()

                root.setOnClickListener {
                    onItemClickListener.invoke(note)
                }

                root.setOnLongClickListener {
                    deleteNote = note
                    onLongItemClickListener.invoke(note)
                    true
                }
            }
        }

    }
}
package com.example.m7l1.presentation.notes


import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.m7l1.R
import com.example.m7l1.databinding.FragmentNotesBinding
import com.example.m7l1.domain.model.Note
import com.example.m7l1.presentation.base.BaseFragment
import com.example.m7l1.presentation.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    override val vm: NotesViewModel by viewModels()
    private val adapter by lazy { NotesAdapter(this::onItemClick, this::onLongItemClick) }

    private val list = mutableListOf<Note>()

    private fun onItemClick(note: Note) {
        val bundle = bundleOf().apply {
            putSerializable(ARG_ADD_EDIT, note)
        }
        findNavController().navigate(R.id.action_notesFragment_to_addFragment, bundle)
    }

    private fun onLongItemClick(note: Note) {
        vm.delete(note)
    }

    override fun initialize() {
        binding.notesRv.adapter = adapter
    }

    override fun listeners() {
        binding.createBtn.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_addFragment)
        }
    }

    override fun setupRequest() {
        vm.noteState.collectState(onLoading = {
            binding.progressBar.isVisible = true
        }, onError = {
            binding.progressBar.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.progressBar.isVisible = false
            adapter.updateList(it as MutableList<Note>)
        })

        vm.deleteNoteState.collectState(onLoading = {
            binding.progressBar.isVisible = true
        }, onError = {
            binding.progressBar.isVisible = false
            showToast(it)
        }, onSuccess = {
            binding.progressBar.isVisible = false
            showToast("Note is successfully deleted!")
            adapter.delete()
        })

        vm.createNoteState.collectState(onLoading = {
            binding.progressBar.isVisible = true
        }, onError = {
            binding.progressBar.isVisible = false
        }, onSuccess = {
            binding.progressBar.isVisible = false
            //adapter.updateList(it as MutableList<Note>)

        })

    }



    companion object{
        const val ARG_ADD_EDIT = "edit_note"
    }
}
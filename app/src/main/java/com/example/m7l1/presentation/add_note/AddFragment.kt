package com.example.m7l1.presentation.add_note


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.m7l1.R
import com.example.m7l1.databinding.FragmentAddBinding
import com.example.m7l1.domain.model.Note
import com.example.m7l1.presentation.base.BaseFragment
import com.example.m7l1.presentation.extension.showToast
import com.example.m7l1.presentation.notes.NotesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : BaseFragment<AddViewModel, FragmentAddBinding>(FragmentAddBinding::inflate) {

    override val vm: AddViewModel by viewModels()
    private var note: Note? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun initialize() {
        if (arguments != null) {
            note = requireArguments()!!.getSerializable(NotesFragment.ARG_ADD_EDIT
                , Note::class.java)
            setData()
        }
    }

    private fun setData() {
        with(binding){
            addTitleEt.setText(note!!.title)
            addDescriptionEt.setText(note!!.description)
        }
    }

    override fun listeners() {
        with(binding) {
            addSaveBtn.setOnClickListener {
                if (note != null) {
                    vm.update(
                        note!!.copy(
                            title = addTitleEt.toString(),
                            description = addDescriptionEt.toString()
                        )
                    )
                } else vm.create(
                    addTitleEt.text.toString(),
                    addDescriptionEt.text.toString()
                )
                findNavController().navigate(R.id.action_addFragment_to_notesFragment)
            }
        }
    }

    override fun setupRequest() {
        vm.createNoteState.collectState(
            onLoading = {
                binding.progressBar.isVisible = true
            },
            onError = {
                showToast(it)
                binding.progressBar.isVisible = false
            },
            onSuccess = {
                binding.progressBar.isVisible = false
                showToast("Note is successfully created!")
                findNavController().navigateUp()
            }
        )

        vm.editNoteState.collectState(
            onLoading = {
                binding.progressBar.isVisible = true
            },
            onError = {
                binding.progressBar.isVisible = false
                showToast(it)
            },
            onSuccess = {
                binding.progressBar.isVisible = false
                showToast("Note is successfully edited!")
                findNavController().navigateUp()
            }
        )
    }
}
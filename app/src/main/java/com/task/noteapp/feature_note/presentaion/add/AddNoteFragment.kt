package com.task.noteapp.feature_note.presentaion.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView
import com.task.noteapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private val addNoteViewModel: AddNoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(
            R.layout.fragment_add_note,
            container, false
        )
        val colorLayout: LinearLayout = rootView.findViewById(R.id.layoutColors)
        for (noteColor in NoteColor.values()) {
            val colorButton: MaterialButton? = context?.let {
                MaterialButton(it).apply {
                    setBackgroundColor(noteColor)
                    setCornerRadiusResource(R.dimen.rounded_button_radius)
                    height = resources.getDimension(R.dimen.color_layout_height).toInt()
                    width = resources.getDimension(R.dimen.roundedButtonWidth).toInt()
                    RxView.clicks(this).subscribe {
                        addNoteViewModel.onEvent(AddEvent.SelectedColor(noteColor))
                    }
                }
            }
            colorLayout.addView(colorButton)
        }
        RxTextView.afterTextChangeEvents(rootView.findViewById(R.id.editTextHead)).subscribe {
            addNoteViewModel.onEvent(AddEvent.EnteredHead(it.editable().toString()))
        }
        RxTextView.afterTextChangeEvents(rootView.findViewById(R.id.editTextBody)).subscribe {
            addNoteViewModel.onEvent(AddEvent.EnteredBody(it.editable().toString()))
        }
        RxTextView.afterTextChangeEvents(rootView.findViewById(R.id.editTextURL)).subscribe {
            addNoteViewModel.onEvent(AddEvent.EnteredURL(it.editable().toString()))
        }
        return rootView
    }

    private fun MaterialButton.setBackgroundColor(noteColor: NoteColor) {
        setBackgroundColor(
            ContextCompat.getColor(
                context,
                noteColor.getBodyColor()
            )
        )
    }
}

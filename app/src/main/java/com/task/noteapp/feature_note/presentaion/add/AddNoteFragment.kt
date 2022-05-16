package com.task.noteapp.feature_note.presentaion.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.task.noteapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private lateinit var layoutNote: LinearLayout
    private lateinit var buttonAdd: MaterialButton
    private lateinit var editTextBody: EditText
    private lateinit var textViewDate: TextView
    private lateinit var editTextURL: EditText
    private lateinit var editTextHead: EditText
    private lateinit var imageViewImage: ImageView

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
        initViews(rootView)

        addNoteViewModel.uiStateLiveData().observe(viewLifecycleOwner, Observer {
            textViewDate.text = it.date
            layoutNote.setBackgroundColorWithInt(NoteColor.values()[it.color].getBodyColor())
            if (it.isUrlValid) {
                Glide.with(this).load(it.url).into(imageViewImage)
            }
        })

        addNoteViewModel.firstTimeLoad()
        return rootView
    }

    private fun initViews(rootView: View) {

        val colorLayout: LinearLayout = rootView.findViewById(R.id.layoutColors)
        for (noteColor in NoteColor.values()) {
            val colorButton: MaterialButton? = context?.let {
                MaterialButton(it).apply {
                    setBackgroundColor(noteColor)
                    setCornerRadiusResource(R.dimen.rounded_button_radius)
                    height = resources.getDimension(R.dimen.color_layout_height).toInt()
                    width = resources.getDimension(R.dimen.roundedButtonWidth).toInt()
                    setOnClickListener {
                        addNoteViewModel.onEvent(AddEvent.SelectedColor(noteColor))
                    }
                }
            }
            colorLayout.addView(colorButton)
        }
        imageViewImage = rootView.findViewById(R.id.imageViewImage)
        editTextHead = rootView.findViewById(R.id.editTextHead)
        editTextURL = rootView.findViewById(R.id.editTextURL)
        editTextBody = rootView.findViewById(R.id.editTextBody)
        buttonAdd = rootView.findViewById(R.id.buttonAdd)
        layoutNote = rootView.findViewById(R.id.layoutNote)
        textViewDate = rootView.findViewById(R.id.textViewDate)

        editTextHead.apply {
            addTextChangedListener {
                addNoteViewModel.onEvent(AddEvent.EnteredHead(it.toString()))
            }
        }
        editTextBody.apply {
            addTextChangedListener {
                addNoteViewModel.onEvent(AddEvent.EnteredBody(it.toString()))
            }
        }
        editTextURL.apply {
            addTextChangedListener {
                addNoteViewModel.onEvent(AddEvent.EnteredURL(it.toString()))
            }
        }

        buttonAdd.apply {
            setOnClickListener {
                addNoteViewModel.onEvent(AddEvent.SaveNote)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNoteViewModel.firstTimeLoad()
    }

    private fun MaterialButton.setBackgroundColor(noteColor: NoteColor) {
        setBackgroundColor(
            ContextCompat.getColor(
                context,
                noteColor.getBodyColor()
            )
        )
    }

    private fun LinearLayout.setBackgroundColorWithInt(color: Int) {
        setBackgroundColor(
            ContextCompat.getColor(
                context,
                color
            )
        )
    }
}


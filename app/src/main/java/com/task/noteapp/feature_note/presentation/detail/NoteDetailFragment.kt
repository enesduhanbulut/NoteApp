package com.task.noteapp.feature_note.presentation.detail

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.task.noteapp.R
import com.task.noteapp.feature_note.presentation.NoteColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    private lateinit var buttonDelete: Button
    private lateinit var viewType: ViewType
    private lateinit var layoutNote: LinearLayout
    private lateinit var buttonAdd: MaterialButton
    private lateinit var editTextBody: EditText
    private lateinit var textViewDate: TextView
    private lateinit var editTextURL: EditText
    private lateinit var editTextHead: EditText
    private lateinit var imageViewImage: ImageView
    private val noteDetailViewModel: NoteDetailViewModel by viewModels()
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(
            R.layout.fragment_note_detail,
            container, false
        )
        viewType = ViewType.EDIT
        if (args.noteId == -1) {
            viewType = ViewType.ADD
        }
        initViews(rootView)
        noteDetailViewModel.uiStateLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is NoteDetailUIEvent.UpdateUI -> {
                    editTextHead.setText(it.value.head)
                    textViewDate.text = it.value.date
                    editTextBody.setText(it.value.body)
                    editTextURL.setText(it.value.url)
                    layoutNote.setBackgroundColorWithInt(it.value.color.getBodyColor())
                }
                is NoteDetailUIEvent.DeleteNote -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.delete_note_title))
                        .setMessage(getString(R.string.delete_note_warning))
                        .setPositiveButton(getString(R.string.Yes)) { dialog, which ->
                            noteDetailViewModel.onEvent(NoteDetailEvent.DeleteNote)
                            Snackbar.make(
                                requireView(),
                                getString(R.string.note_deleted),
                                Snackbar.LENGTH_LONG
                            ).show()
                            requireActivity().onBackPressed()
                        }
                        .setNegativeButton(getString(R.string.No)) { dialog, which ->
                            dialog.dismiss()
                        }
                        .create().show()
                }
                is NoteDetailUIEvent.ShowImage -> {
                    Glide.with(this).asBitmap().load(it.value).into(imageViewImage)
                    imageViewImage.visibility = View.VISIBLE
                }
                is NoteDetailUIEvent.ClearImage -> {
                    Glide.with(this).clear(imageViewImage)
                }
                is
                NoteDetailUIEvent.ShowError -> {
                    Snackbar.make(layoutNote, it.message, Snackbar.LENGTH_LONG).show()
                }
                is NoteDetailUIEvent.ShowMessage -> {
                    Snackbar.make(layoutNote, it.message, Snackbar.LENGTH_LONG).show()
                }
                is NoteDetailUIEvent.Finish -> {
                    Navigation.findNavController(requireView()).navigate(
                        R.id.action_noteDetailFragment_to_noteListFragment
                    )
                }
            }
        }

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
                        noteDetailViewModel.onEvent(NoteDetailEvent.SelectedColor(noteColor))
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
        buttonDelete = rootView.findViewById(R.id.buttonDelete)
        layoutNote = rootView.findViewById(R.id.layoutNote)
        textViewDate = rootView.findViewById(R.id.textViewDate)

        editTextHead.apply {
            addTextChangedListener {
                noteDetailViewModel.onEvent(NoteDetailEvent.EnteredHead(it.toString()))
            }
        }
        editTextBody.apply {
            addTextChangedListener {
                noteDetailViewModel.onEvent(NoteDetailEvent.EnteredBody(it.toString()))
            }
        }
        editTextURL.apply {
            addTextChangedListener {
                noteDetailViewModel.onEvent(NoteDetailEvent.EnteredURL(it.toString()))
            }
        }

        buttonAdd.apply {
            setOnClickListener {
                if (viewType == ViewType.ADD) {
                    noteDetailViewModel.onEvent(NoteDetailEvent.ClickedSave)
                } else {
                    noteDetailViewModel.onEvent(NoteDetailEvent.ClickedUpdate)
                }
            }
            text = if (viewType == ViewType.ADD) {
                getString(R.string.add)
            } else {
                getString(R.string.edit)
            }
        }
        if (viewType == ViewType.EDIT) {
            buttonDelete.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    noteDetailViewModel.onEvent(NoteDetailEvent.ClickedDelete)
                }
                visibility = View.VISIBLE
            }
        } else {
            buttonDelete.visibility = View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteDetailViewModel.onEvent(NoteDetailEvent.Init(viewType, args.noteId))
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


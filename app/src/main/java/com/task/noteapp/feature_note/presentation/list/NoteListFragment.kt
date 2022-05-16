package com.task.noteapp.feature_note.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.task.noteapp.R
import com.task.noteapp.feature_note.adapter.NoteListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private lateinit var recyclerViewNoteList: RecyclerView
    private val noteListViewModel: NoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(
            R.layout.note_list_fragment,
            container, false
        )
        recyclerViewNoteList = rootView.findViewById(R.id.recyclerViewNoteList)
        recyclerViewNoteList.layoutManager = StaggeredGridLayoutManager(2, 1)

        val adapter = NoteListAdapter {
            noteListViewModel.onEvent(ListEvent.NoteSelected(it))
        }
        recyclerViewNoteList.adapter = adapter
        noteListViewModel.uiStateLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is ListUIEvent.UpdateList -> {
                    adapter.updateList(it.value)
                }
                is ListUIEvent.ShowError -> {
                    // TODO: implement error handling
                }
                is ListUIEvent.ShowMessage -> {
                    // TODO: implement message handling
                }
                is ListUIEvent.ShowNote -> {
                    val action =
                        NoteListFragmentDirections.toDetailPage(it.value.id)
                    Navigation.findNavController(rootView).navigate(action)
                }
            }
        }
        noteListViewModel.onEvent(ListEvent.GetNotes(true))
        return rootView
    }
}
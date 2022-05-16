package com.task.noteapp.feature_note.presentaion.list

import android.R.attr.numColumns
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        val adapter = NoteListAdapter()
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
                    // TODO: navigate to note detail screen
                }
            }
        }
        noteListViewModel.onEvent(ListEvent.GetNotes(true))
        return rootView
    }
}
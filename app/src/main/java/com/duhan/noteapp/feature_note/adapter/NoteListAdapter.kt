package com.duhan.noteapp.feature_note.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duhan.noteapp.R
import com.duhan.noteapp.feature_note.domain.FormattedDate
import com.duhan.noteapp.feature_note.domain.model.Note
import com.duhan.noteapp.feature_note.presentation.NoteColor


class NoteListAdapter(private val clickListener: (Note) -> Unit) :
    RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    private var notes: List<Note> = emptyList()


    class ViewHolder(private val view: View, private val clickListener: (Note) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val textViewHead: TextView = view.findViewById(R.id.textViewHead)
        private val textViewContent: TextView = view.findViewById(R.id.textViewtBody)
        private val textViewDate: TextView = view.findViewById(R.id.textViewDate)
        private val imageViewImage: ImageView = view.findViewById(R.id.imageViewImage)
        private val layoutNote: LinearLayout = view.findViewById(R.id.layoutNote)
        private val imageButtonEdited: ImageButton = view.findViewById(R.id.imageButtonEdited)
        fun bind(note: Note) {
            textViewHead.text = note.head
            textViewContent.text = note.body
            textViewDate.text = FormattedDate.formatDate(note.createDate)
            Glide.with(view).asBitmap().load(note.url).into(imageViewImage)
            note.isEdited.let {
                if (it) {
                    imageButtonEdited.visibility = View.VISIBLE
                    imageButtonEdited.setOnClickListener {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            imageButtonEdited.tooltipText = "Last Edit Time \n " + FormattedDate.formatDate(
                                note.lastModifiedDate
                            ) + " "
                        }
                    }
                } else {
                    imageButtonEdited.visibility = View.GONE
                }
            }
            layoutNote.setBackgroundColor(
                ContextCompat.getColor(
                    view.context,
                    NoteColor.values()[note.color].getBodyColor()
                )
            )
            layoutNote.setOnClickListener {
                clickListener.invoke(note)
            }
        }
    }

    fun updateList(newList: List<Note>) {
        val callback: DiffUtil.Callback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return itemCount
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return notes[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return notes[oldItemPosition].head == newList[newItemPosition].head &&
                        notes[oldItemPosition].body == newList[newItemPosition].body &&
                        notes[oldItemPosition].createDate == newList[newItemPosition].createDate &&
                        notes[oldItemPosition].url == newList[newItemPosition].url
            }
        }
        val diffResult = DiffUtil.calculateDiff(callback)
        this.notes = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_note_list_item, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
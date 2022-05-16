package com.task.noteapp.feature_note.presentaion.add

import com.task.noteapp.R

enum class NoteColor(private val bodyColor: Int,
                     private val headColor: Int) {
    ORANGE(R.color.orange_post_it_body, R.color.orange_post_it_head),
    YELLOW(R.color.yellow_post_it_head, R.color.yellow_post_it_head),
    PURPLE(R.color.purple_post_it_head, R.color.purple_post_it_head),
    ;

    fun getBodyColor(): Int {
        return bodyColor
    }
    fun getHeadColor(): Int {
        return headColor
    }

}
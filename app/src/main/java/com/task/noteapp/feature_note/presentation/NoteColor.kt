package com.task.noteapp.feature_note.presentation

import com.task.noteapp.R

enum class NoteColor(
    private val bodyColor: Int,
    private val headColor: Int
) {
    ORANGE(R.color.orange_post_it_body, R.color.orange_post_it_head),
    YELLOW(R.color.yellow_post_it_body, R.color.yellow_post_it_head),
    PURPLE(R.color.purple_post_it_body, R.color.purple_post_it_head),
    ;

    fun getBodyColor(): Int {
        return bodyColor
    }

    fun getHeadColor(): Int {
        return headColor
    }
}
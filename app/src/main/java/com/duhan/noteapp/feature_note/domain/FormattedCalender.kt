package com.duhan.noteapp.feature_note.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object FormattedDate {
    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: Date): String {
        return SimpleDateFormat("dd/MM/yyyy").format(date)
    }

    fun newInstance(): String {
        val cal = Calendar.getInstance()
        return formatDate(cal.time)
    }
}
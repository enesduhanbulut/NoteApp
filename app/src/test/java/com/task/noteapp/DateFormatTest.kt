package com.task.noteapp

import com.task.noteapp.feature_note.domain.FormattedDate
import java.text.SimpleDateFormat
import java.util.*

class DateFormatTest {
    @org.junit.Test
    fun formatDate() {
        val date = 4
        val month = 10
        val year = 2022
        val calender = Calendar.getInstance()
        calender.set(Calendar.MONTH, month)
        calender.set(Calendar.DAY_OF_MONTH, date)
        calender.set(Calendar.YEAR, year)
        assert(FormattedDate.formatDate(calender.time) == "04/11/2022")
    }

    fun newInstanceTest() {
        val calender = Calendar.getInstance()
        val formatted = FormattedDate.formatDate(
            calender.time
        )
        assert(formatted == SimpleDateFormat("dd/MM/yyyy").format(calender.time))
    }
}
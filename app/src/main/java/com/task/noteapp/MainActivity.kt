package com.task.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task.noteapp.feature_note.presentaion.add.AddNoteFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, AddNoteFragment())
            .commit()
    }
}
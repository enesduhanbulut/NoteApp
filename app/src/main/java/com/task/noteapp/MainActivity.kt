package com.task.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.listPage
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.listPage -> Navigation.findNavController(this,R.id.fragmentContainer)
                    .navigate(R.id.noteListFragment)
                R.id.addPage -> Navigation.findNavController(this,R.id.fragmentContainer)
                    .navigate(R.id.noteDetailFragment)
            }
            true
        }
    }
    private fun clearBackStack(){
        val fm: FragmentManager = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        }
    }
}
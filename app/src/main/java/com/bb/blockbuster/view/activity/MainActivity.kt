package com.bb.blockbuster.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bb.blockbuster.R
import com.bb.blockbuster.view.fragment.CartFragment
import com.bb.blockbuster.view.fragment.MoviesListFragmentDirections


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onSupportNavigateUp() = findNavController(R.id.main_activity_nav_host_fragment).navigateUp()

}
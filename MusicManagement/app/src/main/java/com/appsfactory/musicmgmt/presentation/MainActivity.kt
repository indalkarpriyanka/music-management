package com.appsfactory.musicmgmt.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.appsfactory.musicmgmt.MyApplication
import com.appsfactory.musicmgmt.R
import com.appsfactory.musicmgmt.common.ActivityCompositeRoot
import com.appsfactory.musicmgmt.databinding.ActivityMainBinding

import com.appsfactory.musicmgmt.presentation.viewModels.TopAlbumsViewModel

class MainActivity : AppCompatActivity() {

    private val appCompositeRoot get() = (application as MyApplication).applicationCompositeRoot
    val compositeRoot get() = ActivityCompositeRoot(this, appCompositeRoot)

    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setNavController()
    }

    private fun setNavController() {
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        setupActionBarWithNavController(navController, null)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


}
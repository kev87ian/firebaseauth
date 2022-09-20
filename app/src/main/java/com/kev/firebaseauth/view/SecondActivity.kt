package com.kev.firebaseauth.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.kev.firebaseauth.R

class SecondActivity : AppCompatActivity() {
	private lateinit var navController: NavController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_second)

		val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_ingine) as NavHostFragment
		navController = navHostFragment.findNavController()
		setupActionBarWithNavController(navController)
	}
}
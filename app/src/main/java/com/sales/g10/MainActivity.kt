package com.sales.g10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.sales.g10.fragment.SignUpFragment

class MainActivity : AppCompatActivity(),SignUpFragment.OnListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onBack() {
        onBackPressed()
    }
}

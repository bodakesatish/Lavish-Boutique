package com.bodakesatish.templates

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bodakesatish.templates.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.dest_service_list
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            setFragmentTitle(destination.id)
            if (destination.id == R.id.navigation_home || destination.id == R.id.navigation_dashboard || destination.id == R.id.dest_service_list) {
                //binding.navView.visibility = View.VISIBLE
                binding.navView.animate().translationY(0f).alpha(1f).setDuration(300)
                    .withStartAction {
                        binding.navView.visibility = View.VISIBLE
                    }
            } else {
                //binding.navView.visibility = View.GONE
                binding.navView.animate().translationY(binding.navView.height.toFloat()).alpha(0f)
                    .setDuration(300).withEndAction {
                    binding.navView.visibility = View.GONE
                }
            }
        }

        binding.headerGeneric.btnBack.setOnClickListener {
            navController.popBackStack()
        }

    }

    private fun setFragmentTitle(id: Int) {
        binding.headerGeneric.btnBack.visibility = View.GONE
        when(id) {
            R.id.navigation_home -> {
                binding.headerGeneric.tvHeader.text = "Home"
            }
            R.id.navigation_dashboard -> {
                binding.headerGeneric.tvHeader.text = "Customer"
            }
            R.id.dest_service_list -> {
                binding.headerGeneric.tvHeader.text = "Services"
            }
            R.id.dest_create_job -> {
                binding.headerGeneric.tvHeader.text = "Create Job"
                binding.headerGeneric.btnBack.visibility = View.VISIBLE
            }
            R.id.dest_create_customer -> {
                binding.headerGeneric.tvHeader.text = "Add Customer"
                binding.headerGeneric.btnBack.visibility = View.VISIBLE
            }
            R.id.dest_create_service -> {
                binding.headerGeneric.tvHeader.text = "Add Service"
                binding.headerGeneric.btnBack.visibility = View.VISIBLE
            }
        }
    }

}
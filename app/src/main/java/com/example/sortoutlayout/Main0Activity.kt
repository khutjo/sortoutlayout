package com.example.sortoutlayout

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sortoutlayout.databinding.ActivityMain0Binding

import org.json.JSONException

class Main0Activity : AppCompatActivity() {
    private var mainMenu: Menu? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain0Binding
    private var requestQueue: RequestQueue? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain0Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)




// Add the request to the RequestQueue.


        val navController = findNavController(R.id.nav_host_fragment_content_main3)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId){
            R.id.action_settings -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main3)
                navController.navigateUp(appBarConfiguration)
                navController.navigate(R.id.settingsFragment);
//                mainMenu.
            }
            R.id.faq -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main3)
                navController.navigateUp(appBarConfiguration)
                navController.navigate(R.id.faqFragment);
            }
            R.id.about -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main3)
                navController.navigateUp(appBarConfiguration)
                navController.navigate(R.id.aboutFragment);
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main3)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mainMenu = menu
        return super.onCreateOptionsMenu(menu)
    }



}
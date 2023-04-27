package com.example.apitest.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.apitest.R
import com.example.apitest.databinding.ActivityMainBinding
import com.onesignal.OneSignal
import dagger.hilt.android.AndroidEntryPoint

const val ONESIGNAL_APP_ID = "b7224024-b944-4823-9572-fedc5b1dbb70"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableOneSignal()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottomNavViewItemSelected()
    }

    /**
     * включение и подписка oneSignal
     */
    private fun enableOneSignal(){
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        OneSignal.promptForPushNotifications()
    }

    private fun bottomNavViewItemSelected(){
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mainFragment -> {
                    navController!!.navigate(R.id.mainFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.webviewFragment -> {
                    navController!!.navigate(R.id.webviewFragment)
                    return@setOnItemSelectedListener true
                }
                else -> {return@setOnItemSelectedListener false}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
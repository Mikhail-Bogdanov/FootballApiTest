package com.example.apitest.presentation.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.apitest.R
import com.example.apitest.databinding.ActivityMainBinding
import com.example.apitest.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottomNavViewItemSelected()


        //TODO в хилте onSaveInstance
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

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
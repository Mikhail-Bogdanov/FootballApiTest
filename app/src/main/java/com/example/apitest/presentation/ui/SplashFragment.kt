package com.example.apitest.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.apitest.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_mainFragment)

        }

        //TODO сделать подгрузку
        //TODO можно сделать подгрузку через вью модель активности
        //TODO splash api in android 12
        //я гений сделал без этого

        //TODO попробовать сделать кеширование и загрузку из БД

        return view
    }


}
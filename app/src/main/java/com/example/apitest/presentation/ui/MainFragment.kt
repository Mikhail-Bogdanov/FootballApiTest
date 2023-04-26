package com.example.apitest.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apitest.adapters.RecyclerViewMatchesAdapter
import com.example.apitest.databinding.FragmentMainBinding
import com.example.apitest.presentation.viewModels.MainViewModel
import com.example.data.footballModel.FootballModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createUI()

        subscribe()

        mainViewModel.getStatistic()

    }

    /**
     * обработка нажатий на айтемы rv
     */
    private fun handleClick(pos: Int) : Int{

        return pos
    }

    /**
     * подписка на обновление данных в rv
     *
     */
    private fun subscribe(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mainViewModel.stateFlowStat.collect{
                    if(it != null){
                        (binding.rvMatches.adapter as RecyclerViewMatchesAdapter)
                            .updateData(it)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    /**
     * функция для объединения создания ui и настройки обработчиков нажатий
     */
    private fun createUI() = with(binding){
        with(rvMatches){
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewMatchesAdapter(
                listOf(),
                ::handleClick
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
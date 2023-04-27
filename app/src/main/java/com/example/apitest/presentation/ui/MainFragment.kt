package com.example.apitest.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apitest.R
import com.example.apitest.adapters.RecyclerViewMatchesAdapter
import com.example.apitest.databinding.FragmentMainBinding
import com.example.apitest.presentation.viewModels.MainViewModel
import com.example.data.utility.Event
import com.google.android.material.snackbar.Snackbar
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
        subscribeOnFlow()
        subscribeOnEvents()

        mainViewModel.getData()
    }

    /**
     * подписка на обновление данных в rv
     */
    private fun subscribeOnFlow(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mainViewModel.stateFlowStat.collect{
                    if(it != null)
                        (binding.rvMatches.adapter as RecyclerViewMatchesAdapter)
                            .updateData(it)
                }
            }
        }
    }

    /**
     * подписка на получение нкоторых ивентов
     */
    private fun subscribeOnEvents(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mainViewModel.eventsFlow.collect{ event ->
                    when(event){
                        is Event.PositiveFeedback -> {
                            //do nothing
                        }
                        is Event.NegativeFeedback -> {
                            Snackbar.make(
                                requireView(),
                                event.text,
                                Snackbar.LENGTH_LONG
                            ).apply {
                                anchorView = requireActivity().findViewById(R.id.bottom_navigation)
                            }.show()
                        }
                    }
                }
            }
        }
    }

    private fun createUI() = with(binding){
        with(rvMatches){
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewMatchesAdapter(listOf())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
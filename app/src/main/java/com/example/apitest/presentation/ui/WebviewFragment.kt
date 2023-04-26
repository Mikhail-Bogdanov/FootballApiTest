package com.example.apitest.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apitest.databinding.FragmentWebviewBinding
import com.example.apitest.presentation.CustomWebViewClient
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebviewFragment : Fragment() {

    private var _binding: FragmentWebviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createUI()
    }

    private fun createUI() = with(binding){
        with(webView) {
            loadUrl("https://github.com/Mikhail-Bogdanov")
            settings.displayZoomControls = false
            settings.javaScriptEnabled = true
            webViewClient = CustomWebViewClient()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
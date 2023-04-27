package com.example.apitest

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.widget.ContentLoadingProgressBar

class CustomWebViewClient : WebViewClient() {

    /**
     * функция чтобы все url'ы открывались в одном webview
     */
    override fun shouldOverrideUrlLoading(
        view: WebView,
        request: WebResourceRequest
    ): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }

    /**
     * отображение лоадера
     */
    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        view.visibility = View.GONE
        view.rootView.findViewById<ContentLoadingProgressBar>(R.id.loading_progress_bar).show()
    }

    /**
     * отображение webview
     */
    override fun onPageFinished(view: WebView, url: String?) {
        super.onPageFinished(view, url)
        view.visibility = View.VISIBLE
        view.rootView.findViewById<ContentLoadingProgressBar>(R.id.loading_progress_bar).hide()
    }
}
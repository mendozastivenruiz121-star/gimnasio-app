package com.example.gimnasio

import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {
    private lateinit var web: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        web = WebView(this)
        setContentView(web)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.webViewClient = WebViewClient()
        web.webChromeClient = WebChromeClient()
        val html = assets.open("index.html").bufferedReader().use { it.readText() }
        val cfg = assets.open("config.js").bufferedReader().use { it.readText() }
        web.loadDataWithBaseURL(
            "https://gimnasio.app/",
            html.replace("<!--CONFIG-->", "<script>" + cfg + "</script>"),
            "text/html", "UTF-8", null
        )
    }
}

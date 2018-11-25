package com.example.mahesh.webviewex
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var et1:EditText? = null
    var wview:WebView? = null
    var pDialog:ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et1 = findViewById(R.id.et1)
        wview = findViewById(R.id.wview)
        wview?.settings?.javaScriptEnabled = true
        wview?.settings?.builtInZoomControls = true
        wview?.addJavascriptInterface(this,"abc")
        pDialog = ProgressDialog(this@MainActivity)
        pDialog?.setTitle("Message")
        var message = pDialog?.setMessage("Please wait page is loading...")
        wview?.webViewClient = object:WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pDialog?.show()
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pDialog?.dismiss()

            }
        }
    }

    fun  load(v:View){
        when(v.id){
            R.id.search ->
                wview?.loadUrl(et1!!.text.toString())
            R.id.Fb ->
                wview?.loadUrl("http://www.facebook.com")
            R.id.Google ->
                wview?.loadUrl("http://www.google.com")
            R.id.Youtube ->
                wview?.loadUrl("http://www.youtube.com")
            R.id.html ->
                wview?.loadUrl("file:///android_asset/login.html")
        }
    }
    @JavascriptInterface
    private fun display(email:String, pass:String)
    {
        Toast.makeText(this,
                email+"\n"+pass,Toast.LENGTH_LONG).show()
    }

}
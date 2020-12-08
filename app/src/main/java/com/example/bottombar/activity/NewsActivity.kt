package com.example.bottombar.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.CollapsibleActionView
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bottombar.R
import kotlinx.android.synthetic.main.news_content_frag.*


class NewsActivity: AppCompatActivity(){
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_content_frag)
        //跳转传过来的数据
        val title = intent.getStringExtra("title")
        val image = intent.getStringExtra("image")
        val content = intent.getStringExtra("url")
        //图片展示
        Glide.with(this).load(image).into(image_top)
        //标题展示
        coll_too_bar.title = title
        //设置toolbar
        setSupportActionBar(toolbar_top)
        //顶部返回按钮
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        //加载url
        web_view.webChromeClient = WebChromeClient()
        web_view.webViewClient = WebViewClient()
        web_view.settings.javaScriptEnabled = true
        web_view.settings.defaultTextEncodingName = "utf-8"
        if (content != null) {
            web_view.loadUrl(content)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}
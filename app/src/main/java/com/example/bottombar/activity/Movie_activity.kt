package com.example.bottombar.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bottombar.R
import com.example.bottombar.bean.MovieDataClass
import com.google.gson.Gson
import kotlinx.android.synthetic.main.movie_content_frag.*

class Movie_activity:AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_content_frag)
        //跳转传过来的数据
        val JsonData=intent.getStringExtra("movie")
        val movie: MovieDataClass.SubjectsBean.SubjectBean =Gson().fromJson(JsonData,MovieDataClass.SubjectsBean.SubjectBean::class.java)


        //图片展示
        Glide.with(this).load(movie.images.medium.toString()).into(image_top)
        //标题展示
        coll_too_bar.title = movie.title
        //设置toolbar
        setSupportActionBar(toolbar_top)
        //顶部返回按钮
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        Glide.with(this).load(movie.images.large.toString()).into(mIvBgPoster)
        mTvMovieName.text=movie.title
        mTvCountries.text="America"
        mTvTime.text=movie.year
        mTvComment.text=movie.original_title
        Glide.with(this).load(movie.casts[0].avatars.medium.toString()).into(mIvMoviePoster)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}
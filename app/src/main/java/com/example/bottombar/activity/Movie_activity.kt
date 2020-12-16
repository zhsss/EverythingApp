package com.example.bottombar.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bottombar.Adapter.CastAdapter
import com.example.bottombar.R
import com.example.bottombar.bean.MovieDataClass
import com.google.gson.Gson
import kotlinx.android.synthetic.main.movie_content_frag.*
import kotlinx.android.synthetic.main.weather_fragment.*

class Movie_activity:AppCompatActivity() {


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_content_frag)
        //跳转传过来的数据
        val JsonData=intent.getStringExtra("movie")
        val movie: MovieDataClass.SubjectsBean.SubjectBean =Gson().fromJson(JsonData,MovieDataClass.SubjectsBean.SubjectBean::class.java)
        //图片展示
        Glide.with(this).load(movie.images.large.toString()).into(image_top)
        //标题展示
        coll_too_bar.title = movie.title
        //设置toolbar
        setSupportActionBar(toolbar_top)
        //顶部返回按钮
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        Glide.with(this).load(movie.images.medium.toString()).into(mIvBgPoster)
        mTvMovieName.text=movie.title
        mTvCountries.text="America"
        mTvTime.text=movie.year
        mTvComment.text=movie.original_title
        Glide.with(this).load(movie.casts[0].avatars.medium.toString()).into(mIvMoviePoster)

        mTvScore.text="评分："+movie.rating.average.toString()
        mTvDirectors.text="导演："+movie.directors[0].name_en
        mTvCasts.text="主演："+movie.casts[0].name_en
        mTvGenres.text="类型："+movie.genres[0]

        if(movie.durations==null){
            mTvMainTime.text="大陆上映日期：暂无"
        }else{
            mTvMainTime.text="大陆上映日期："+movie.mainland_pubdate
        }

        mTvDuring.text="电影片长："+movie.durations[0]
        initRecyclerView(movie)
    }

    private fun initRecyclerView(dataClass: MovieDataClass.SubjectsBean.SubjectBean) {
        runOnUiThread {
            val horizontal =LinearLayoutManager(this)
            horizontal.orientation=LinearLayoutManager.HORIZONTAL
            mCastsRecyclerView.layoutManager = horizontal
            mCastsRecyclerView.adapter = CastAdapter(
                    this,
                    dataClass.casts as ArrayList<MovieDataClass.SubjectsBean.SubjectBean.CastsBean>
            )
            (mCastsRecyclerView.adapter as CastAdapter).notifyDataSetChanged()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}
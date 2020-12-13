package com.example.bottombar.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bottombar.Adapter.MovieAdapter
import com.example.bottombar.R
import com.example.bottombar.bean.MovieDataClass
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.android.synthetic.main.movie_fragment.*
class MovieFragment : Fragment()
{
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.movie_fragment, container, false)
        getMovieUs()
        return view
    }
    fun initmovieRecyclerview(msg: MovieDataClass) {
        activity?.runOnUiThread{
                movieRecyclerView.layoutManager = GridLayoutManager(activity, 3)
                movieRecyclerView.adapter = MovieAdapter(
                        activity,
                        msg.subjects as ArrayList<MovieDataClass.SubjectsBean>
                )
                (movieRecyclerView.adapter as MovieAdapter).notifyDataSetChanged()
        }
    }
    /**
     * 请求网络数据
     */
    interface  NewsService{
        @GET("us_box")
        fun getMovieUs(@Query("apikey")apikey:String
        ):retrofit2.Call<MovieDataClass>
    }
    private fun getMovieUs(){
        val retrofit= Retrofit.Builder().baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val newsService=retrofit.create(NewsService::class.java)
        newsService.getMovieUs("0b2bdeda43b5688921839c8ecb20399b")
                .enqueue(object :retrofit2. Callback<MovieDataClass> {
                    override fun onFailure(call: retrofit2.Call<MovieDataClass>, t: Throwable) {
                        t.printStackTrace()
                    }
                    override fun onResponse(call: retrofit2.Call<MovieDataClass>, response:retrofit2.Response<MovieDataClass>) {
                        val msg= response.body()!!
                        initmovieRecyclerview(msg)
                    }

                })
    }
}
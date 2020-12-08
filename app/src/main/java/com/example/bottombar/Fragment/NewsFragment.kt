package com.example.bottombar.Fragment

import android.os.Bundle


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottombar.Adapter.NewsAdapter
import com.example.bottombar.R
import com.example.bottombar.bean.NewsDataClass
import kotlinx.android.synthetic.main.news_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.android.synthetic.main.news_fragment.view.*
class NewsFragment : Fragment()
{



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.news_fragment, container, false)

        getInternetData("")
        return view
    }
    private fun initRecyclerView(dataClass: NewsDataClass) {
        activity?.runOnUiThread {
            newsRecyclerView.layoutManager = LinearLayoutManager(activity)
            newsRecyclerView.adapter =NewsAdapter(
                    activity,
                    dataClass.result!!.data as ArrayList<NewsDataClass.ResultBean.DataBean>
            )
            (newsRecyclerView.adapter as NewsAdapter).notifyDataSetChanged()
        }
    }

    /**
     * 请求网络数据
     */
    interface  NewsService{
        @GET("toutiao/index")
        fun getMessage(@Query("type")type:String
                       ,@Query("key")key:String

        ):retrofit2.Call<NewsDataClass>
    }
    private fun getInternetData(type:String){
        var list:MutableList<NewsDataClass.ResultBean.DataBean> = ArrayList()
        val retrofit= Retrofit.Builder().baseUrl("https://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val newsService=retrofit.create(NewsService::class.java)
        newsService.getMessage(type,"3f0c43b33d06a1d5916fa65e6536eba2")
                .enqueue(object :retrofit2. Callback<NewsDataClass> {
                    override fun onFailure(call: retrofit2.Call<NewsDataClass>, t: Throwable) {
                        t.printStackTrace()
                    }
                    override fun onResponse(call: retrofit2.Call<NewsDataClass>, response:retrofit2.Response<NewsDataClass>) {
                        val msg= response.body()!!

                        initRecyclerView(msg)

                    }

                })

    }
}
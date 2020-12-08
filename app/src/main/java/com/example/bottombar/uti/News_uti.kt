package com.example.bottombar.uti

import com.example.bottombar.activity.News_activity
import com.example.bottombar.bean.NewsDataClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class News_uti {
    companion object{
        interface  NewsService{
            @GET("toutiao/index")
            fun getMessage(@Query("type")type:String
                           ,@Query("key")key:String

            ): Call<NewsDataClass>

        }
        fun sendRequest(type:String):List<NewsDataClass.ResultBean.DataBean>{
            var listdata:MutableList<NewsDataClass.ResultBean.DataBean> = ArrayList()
            val retrofit= Retrofit.Builder().baseUrl("https://v.juhe.cn/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val newsService=retrofit.create(NewsService::class.java)
            newsService.getMessage(type,"3f0c43b33d06a1d5916fa65e6536eba2")
                    .enqueue(object : Callback<NewsDataClass> {
                        override fun onFailure(call: Call<NewsDataClass>, t: Throwable) {
                            t.printStackTrace()
                        }
                        override fun onResponse(call: Call<NewsDataClass>, response: Response<NewsDataClass>) {
                            val msg=response.body()
                            if (msg != null) {
                                listdata= msg.result.data as MutableList<NewsDataClass.ResultBean.DataBean>

                            }
                        }

                    })
            return listdata
        }
    }



}
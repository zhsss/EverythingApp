package com.example.bottombar.Fragment
import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.bottombar.Adapter.NewsAdapter
import com.example.bottombar.Database.NewsDatBaseHelper
import com.example.bottombar.R
import com.example.bottombar.bean.NewsDataClass
import kotlinx.android.synthetic.main.news_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
class NewsFragment : Fragment(),SwipeRefreshLayout.OnRefreshListener
{
    private var newsHelper:NewsDatBaseHelper? = null
    private val listLast:MutableList<NewsDataClass.ResultBean.DataBean> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.news_fragment, container, false)
        newsHelper = activity?.let { NewsDatBaseHelper(it, "newsDataStore.db", 1) }
        getDBData()

        //createDB()
        //addData()
        //deleteData()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result:NewsDataClass.ResultBean= NewsDataClass.ResultBean("fh",listLast)
        val listData: NewsDataClass = NewsDataClass("fhg",result,0)
        if (listData!=null){
            initRecyclerView(listData)
        }
        getInternetData("")
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
                        updateData(msg)
                    }

                })
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

    fun createDB()
    {
        newsHelper?.writableDatabase
    }

    private fun addData()
    {
        val value=ContentValues().apply {
            put("uniquekey","ytjj")
            put("author","er")
            put("date","20278hiu")
            put("title","sd")
            put("category","eewr")
            put("url","dgewuwe")
            put("thumbanill","efew")
        }
        newsHelper?.writableDatabase?.insert("newsData",null,value)
    }
    fun deleteData(){
        newsHelper?.writableDatabase?.delete("newsData","id>?", arrayOf("8"))
    }
   fun updateData(msg: NewsDataClass) {
       val db= newsHelper?.writableDatabase
       for (i in 1 until msg.result.data.size-1){
           val values=ContentValues()
           values.put("uniquekey",msg.result.data[i].uniquekey)
           values.put("author",msg.result.data[i].author_name)
           values.put("date",msg.result.data[i].date)
           values.put("title",msg.result.data[i].title)
           values.put("category",msg.result.data[i].category)
           values.put("url",msg.result.data[i].url)
           values.put("thumbanill",msg.result.data[i].thumbnail_pic_s)
           db?.update("newsData",values,"id=?", arrayOf(i.toString()))
       }
   }
    private fun getDBData()
    {

        val db= newsHelper?.writableDatabase
        val cursor =
                db?.rawQuery("select * from newsData ", null)
        if (cursor != null) {

            if (cursor.moveToFirst()) {
                do {
                        val data:NewsDataClass.ResultBean.DataBean = NewsDataClass.ResultBean.DataBean("","","","","","","")
                        data.title = cursor.getString(cursor.getColumnIndex("title"))
                        data.author_name = cursor.getString(cursor.getColumnIndex("author"))
                        data.thumbnail_pic_s = cursor.getString(cursor.getColumnIndex("thumbanill"))
                        data.category = cursor.getString(cursor.getColumnIndex("category"))
                        data.uniquekey = cursor.getString(cursor.getColumnIndex("uniquekey"))
                        data.url = cursor.getString(cursor.getColumnIndex("url"))
                        data.date = cursor.getString(cursor.getColumnIndex("date"))
                        listLast.add(data)
                }while (cursor.moveToNext())
            // do {
                 //   val name = cursor.getString(cursor.getColumnIndex("name"))
                 //   Log.d("MainActivity", "book name is $name")
               // } while (cursor.moveToNext())
            }
        }
        cursor?.close()
    }
    override fun onRefresh() {

    }
}
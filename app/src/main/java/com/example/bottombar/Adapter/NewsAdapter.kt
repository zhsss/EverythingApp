package com.example.bottombar.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bottombar.R
import com.example.bottombar.activity.NewsActivity
import com.example.bottombar.activity.News_activity
import com.example.bottombar.bean.NewsDataClass
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(private val context: FragmentActivity?, private val alist:List<NewsDataClass.ResultBean.DataBean>):
        RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item,parent,false) )
    }

    override fun getItemCount(): Int {
        return alist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder?.itemView!!){
            tv_news_detail_title.setText(alist[position].title)
           tv_news_detail_author_name.setText(alist[position].author_name)
            tv_news_detail_date.setText(alist[position].date)
            Glide.with(context).load(alist[position].thumbnail_pic_s).into(iv_news_detail_pic)
            holder.itemView.setOnClickListener {
                val intent = Intent(context,NewsActivity::class.java)
                intent.putExtra("title", alist[position].title)
                intent.putExtra("image", alist[position].thumbnail_pic_s)
                intent.putExtra("url", alist[position].url)
                context.startActivity(intent)
            }
        }

    }
}
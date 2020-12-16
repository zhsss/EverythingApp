package com.example.bottombar.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bottombar.R
import com.example.bottombar.bean.MovieDataClass
import kotlinx.android.synthetic.main.casts_movie_item.view.*


class CastAdapter(private val context: Activity, private val alist:List<MovieDataClass.SubjectsBean.SubjectBean.CastsBean>):
        RecyclerView.Adapter<CastAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.casts_movie_item,parent,false) )
    }

    override fun getItemCount(): Int {
        return alist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder?.itemView!!){

            mTvName.text=alist[position].name
            Glide.with(context).load(alist[position].avatars.medium).into(mIvCast)


        }

    }
}
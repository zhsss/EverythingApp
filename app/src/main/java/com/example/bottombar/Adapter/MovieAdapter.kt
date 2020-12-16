package com.example.bottombar.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bottombar.R
import com.example.bottombar.activity.Movie_activity
import com.example.bottombar.activity.NewsActivity
import com.example.bottombar.bean.MovieDataClass
import com.example.bottombar.bean.NewsDataClass
import com.google.gson.Gson
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(private val context: FragmentActivity?, private val alist:List<MovieDataClass.SubjectsBean>):
        RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false) )
    }

    override fun getItemCount(): Int {
        return alist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder?.itemView!!){
            Glide.with(context).load(alist[position].subject.images.medium).into(mMoviePoster)
            mMovieName.text=alist[position].subject.title
            mMovieAverage.text= alist[position].subject.rating.average.toString()
            holder.itemView.setOnClickListener {
                val intent = Intent(context, Movie_activity::class.java)
                intent.putExtra("movie",Gson().toJson(alist[position].subject))

                context.startActivity(intent)
            }
        }
    }
}
package com.example.bottombar.Adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bottombar.R
import com.example.bottombar.bean.WeatherDataClass
import kotlinx.android.synthetic.main.weather_hour_item.view.*

class WeatherHourAdapter(private val context: FragmentActivity?, private val alist:List<WeatherDataClass.ResultBean.HourlyBean>):
        RecyclerView.Adapter<WeatherHourAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_hour_item,parent,false) )
    }

    override fun getItemCount(): Int {
        return alist.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder?.itemView!!){
            hourTemp.text=alist[position].temp+"°C"
            hourTime.text=alist[position].time
            hourWeather.text=alist[position].weather
            var imageName="s"+alist[position].img
            var imageId:Int= context!!.resources.getIdentifier(imageName,"drawable", context!!.packageName)
            Glide.with(context).load(imageId).into(hourImage)
           // Glide.with(context).load(alist[position].subject.images.medium).into(mMoviePoster)
           // mMovieName.text=alist[position].subject.title
           // mMovieAverage.text= alist[position].subject.rating.average.toString()

        }

    }
}
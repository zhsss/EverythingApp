package com.example.bottombar.Fragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bottombar.Adapter.NewsAdapter
import com.example.bottombar.Adapter.WeatherHourAdapter
import com.example.bottombar.R
import com.example.bottombar.bean.WeatherDataClass
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.android.synthetic.main.weather_fragment.*

class WeatherFragment : Fragment(), TextWatcher, View.OnClickListener {

    override  fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et_city.addTextChangedListener(this)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val button:Button=view!!.findViewById(R.id.button_click)

        button.setOnClickListener {
            button_click.setBackgroundColor(666666)
            getInternetData(et_city.text.toString())
            Toast.makeText(context,"查询成功",Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun changeLayout(msg: WeatherDataClass) {
        activity?.runOnUiThread{
            nowTel.text=msg.result.temp+"°"
            nowWeek.text=msg.result.week
            nowWeather.text=msg.result.weather

            var imageName="s"+msg.result.daily[0].day.img
            var imageId:Int= activity!!.resources.getIdentifier(imageName,"drawable", activity!!.packageName)
            Glide.with(activity).load(imageId).into(todayImage)
            today.text="   今天 · "+msg.result.daily[0].day.weather
            tempone.text=msg.result.daily[0].night.templow+"° / "+msg.result.daily[0].day.temphigh+"°"

            imageName="s"+msg.result.daily[1].day.img
            imageId= activity!!.resources.getIdentifier(imageName,"drawable", activity!!.packageName)
            Glide.with(activity).load(imageId).into(tommrodayImage)
            tommroday.text="   明天 · "+msg.result.daily[1].day.weather
            temptwo.text=msg.result.daily[1].night.templow+"° / "+msg.result.daily[1].day.temphigh+"°"

            imageName="s"+msg.result.daily[2].day.img
            imageId= activity!!.resources.getIdentifier(imageName,"drawable", activity!!.packageName)
            Glide.with(activity).load(imageId).into(lastdayImage)
            lastday.text=msg.result.daily[2].week+" · "+msg.result.daily[2].day.weather
            tempthree.text=msg.result.daily[2].night.templow+"° / "+msg.result.daily[2].day.temphigh+"°"

            nowClothes.text=msg.result.index[6].ivalue
            nowSport.text=msg.result.index[1].ivalue
        }
    }
    /**
     * 请求网络数据
     */
    interface  WeatherService{
        @GET("weather/query")
        fun getMessage(@Query("appkey")appkey:String, @Query("city")city:String
        ):retrofit2.Call<WeatherDataClass>
    }
    private fun getInternetData(city:String){
        val retrofit= Retrofit.Builder().baseUrl("https://api.jisuapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val weatherService=retrofit.create(WeatherService::class.java)
        weatherService.getMessage("049a62db50f50606",city)
                .enqueue(object :retrofit2. Callback<WeatherDataClass> {
                    override fun onFailure(call: retrofit2.Call<WeatherDataClass>, t: Throwable) {
                        t.printStackTrace()
                    }
                    override fun onResponse(call: retrofit2.Call<WeatherDataClass>, response:retrofit2.Response<WeatherDataClass>) {
                        val msg= response.body()!!
                        changeLayout(msg)
                        initRecyclerView(msg)
                    } })
    }

    fun initRecyclerView(dataClass: WeatherDataClass) {
        activity?.runOnUiThread {
            val horizontal =LinearLayoutManager(activity)
            horizontal.orientation=LinearLayoutManager.HORIZONTAL
            weatherRecyclerView.layoutManager = horizontal
            weatherRecyclerView.adapter = WeatherHourAdapter(
                    activity,
                    dataClass.result!!.hourly as ArrayList<WeatherDataClass.ResultBean.HourlyBean>
            )
            (weatherRecyclerView.adapter as WeatherHourAdapter).notifyDataSetChanged()
        }
    }

    override fun afterTextChanged(s: Editable?) {
        button_click.isEnabled = et_city.length()>0
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun onClick(v: View?) {

    }

}


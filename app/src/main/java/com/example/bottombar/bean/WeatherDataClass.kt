package com.example.bottombar.bean


data class WeatherDataClass(val status: Int,
                     val msg: String,
                     val result: ResultBean) {

    data class ResultBean(val city: String,//城市
                          val cityid: Int,
                          val citycode: String,
                          val date: String,
                          val week: String,
                          val weather: String,
                          val temp: String,
                          val temphigh: String,
                          val templow: String,
                          val img: String,
                          val humidity: String,
                          val pressure: String,
                          val windspeed: String,
                          val winddirect: String,
                          val windpower: String,
                          val updatetime: String,
                          val index: List<IndexBean>,
                          val aqi: AqiBean,
                          val daily: List<DailyBean>,
                          val hourly: List<HourlyBean>) {

        data class IndexBean(val iname: String,
                             val ivalue: String,
                             val detail: String)

        data class AqiBean(val so2: String,
                           val so224: String,
                           val no2: String,
                           val no224: String,
                           val co: String,
                           val co24: String,
                           val o3: String,
                           val o38: String,
                           val o324: String,
                           val pm10: String,
                           val pm1024: String,
                           val pm2_5: String,
                           val pm2_524: String,
                           val iso2: String,
                           val ino2: String,
                           val ico: String,
                           val io3: String,
                           val io38: String,
                           val ipm10: String,
                           val ipm2_5: String,
                           val aqi: String,
                           val primarypollutant: String,
                           val quality: String,
                           val timepoint: String,
                           val aqiinfo: AqiinfoBean) {

            data class AqiinfoBean(val level: String,
                                   val color: String,
                                   val affect: String,
                                   val measure: String)
        }

        data class DailyBean(val date: String,
                             val week: String,
                             val sunrise: String,
                             val sunset: String,
                             val night: NightBean,
                             val day: DayBean) {

            data class NightBean(val weather: String,
                                 val templow: String,
                                 val img: String,
                                 val winddirect: String,
                                 val windpower: String)

            data class DayBean(val weather: String,
                               val temphigh: String,
                               val img: String,
                               val winddirect: String,
                               val windpower: String)
        }

        data class HourlyBean(val time: String,
                              val weather: String,
                              val temp: String,
                              val img: String)
    }
}

package com.example.bottombar.bean


data class NewsDataClass(val reason: String,
                         val result: ResultBean,
                         val error_code: Int) {

    data class ResultBean(val stat: String,
                          val data: List<DataBean>) {
        data class DataBean(var uniquekey: String,
                            var title: String,
                            var date: String,
                            var category: String,
                            var author_name: String,
                            var url: String,
                            var thumbnail_pic_s: String){

        }
    }
}
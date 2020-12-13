package com.example.bottombar.bean
//北美票房榜
data class MovieDataClass(val date: String,
                     val subjects: List<SubjectsBean>,
                     val title: String) {

    data class SubjectsBean(val box: Int,
                            val new: Boolean,
                            val rank: Int,//影片排行
                            val subject: SubjectBean) {//影片详情

        data class SubjectBean(val rating: RatingBean,//评分
                               val genres: List<String>,//类型
                               val title: String,//标题
                               val casts: List<CastsBean>,//主演
                               val durations: List<String>,//片长
                               val collect_count: Int,
                               val mainland_pubdate: String,//大陆日期
                               val has_video: Boolean,
                               val original_title: String,//原名
                               val subtype: String,
                               val directors: List<DirectorsBean>,//导演
                               val pubdates: List<String>,//上映日期
                               val year: String,//年代
                               val images: ImagesBean,//海报
                               val alt: String,
                               val id: String) {    //影片id

            data class RatingBean(val max: Int,
                                  val average: Double,//平均分
                                  val details: DetailsBean,
                                  val stars: String,
                                  val min: Int) {

                data class DetailsBean(val one: Int,
                                       val two: Int,
                                       val three: Int,
                                       val four: Int,
                                       val five: Int)
            }

            data class CastsBean(val avatars: AvatarsBean,//主演相关
                                 val name_en: String,//英文名
                                 val name: String,//中文名
                                 val alt: String,
                                 val id: String) {

                data class AvatarsBean(val small: String,//演员图片
                                       val large: String,
                                       val medium: String)
            }

            data class DirectorsBean(val avatars: AvatarsBean,//导演相关
                                     val name_en: String,
                                     val name: String,
                                     val alt: String,
                                     val id: String) {

                data class AvatarsBean(val small: String,
                                       val large: String,
                                       val medium: String)
            }

            data class ImagesBean(val small: String,//影片海报
                                  val large: String,
                                  val medium: String)
        }
    }
}
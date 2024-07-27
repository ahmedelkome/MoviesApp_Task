package com.route.domain.models.toprated

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopRated(
    val id:Int=0,
    val title:String?=null,
    val overView:String?=null,
    val vote:Double?=null,
    val posterPath:String?=null
):Parcelable

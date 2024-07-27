package com.route.domain.models.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Search (
    val title:String?=null,
    val overView:String?=null,
    val vote:Double?=null,
    val posterPath:String?=null,
    val releaseDate:String?=null
):Parcelable{
}
package com.route.domain.models.popular

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Popular(
    val id:Int=0,
    val title:String?=null,
    val overView:String?=null,
    val vote:Double?=null,
    val posterPath:String?=null
):Parcelable

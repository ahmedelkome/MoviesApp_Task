package com.route.domain.models.toprated

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "TopRated_Table")
data class TopRated(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name = "Title")
    val title:String?=null,
    @ColumnInfo(name = "overView")
    val overView:String?=null,
    @ColumnInfo(name = "Vote")
    val vote:Double?=null,
    @ColumnInfo(name = "posterPath")
    val posterPath:String?=null,
    val timestamp:Long
):Parcelable

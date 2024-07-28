package com.route.domain.models.popular

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "Popular_Table")
data class Popular(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "Title")
    val title: String? = null,
    @ColumnInfo(name = "overView")
    val overView: String? = null,
    @ColumnInfo(name = "vote")
    val vote: Double? = null,
    @ColumnInfo(name = "posterPath")
    val posterPath: String? = null,
    val timestamp:Long
) : Parcelable

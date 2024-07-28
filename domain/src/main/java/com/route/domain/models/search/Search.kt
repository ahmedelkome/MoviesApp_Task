package com.route.domain.models.search

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Search_Table")
data class Search(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "Title")
    val title: String? = null,
    @ColumnInfo(name ="overView")
    val overView: String? = null,
    @ColumnInfo(name ="vote")
    val vote: Double? = null,
    @ColumnInfo(name ="posterPath")
    val posterPath: String? = null,
    @ColumnInfo(name ="releaseDate")
    val releaseDate: String? = null,
    val timestamp:Long
) : Parcelable {
}
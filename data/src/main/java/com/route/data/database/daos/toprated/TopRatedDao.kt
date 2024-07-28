package com.route.data.database.daos.toprated

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.route.domain.models.toprated.TopRated

@Dao
interface TopRatedDao {

    @Insert
    suspend fun insertAllTopRated(listOfPopular:List<TopRated>)

    @Query("SELECT * FROM TopRated_Table")
    suspend fun getAllTopRated():List<TopRated>

    @Query("DELETE FROM TopRated_Table WHERE (strftime('%s', 'now') - timestamp) >= :expiryTime")
    suspend fun invalidateCache(expiryTime: Long)
}
package com.route.data.database.daos.popular

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.route.domain.models.popular.Popular

@Dao
interface PopularDao {

    @Insert
    suspend fun insertAllPopular(listOfPopular:List<Popular>)

    @Query("SELECT * FROM Popular_Table")
    suspend fun getAllPopular():List<Popular>

    @Query("DELETE FROM Popular_Table WHERE (strftime('%s', 'now') - timestamp) >= :expiryTime")
    suspend fun invalidateCache(expiryTime: Long)
}
package com.route.data.database.daos.popular

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.route.domain.models.popular.Popular
import com.route.domain.models.toprated.TopRated

@Dao
interface PopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPopular(listOfPopular: List<Popular>)

    @Query("SELECT * FROM Popular_Table")
    suspend fun getAllPopular(): List<Popular>

    @Query("DELETE FROM Popular_Table WHERE (strftime('%s', 'now') - timestamp) >= :expiryTime")
    suspend fun invalidateCache(expiryTime: Long)

    @Query("DELETE FROM Popular_Table")
    suspend fun clearList()

    @Transaction
    suspend fun replaceData(data: List<Popular>) {
        clearList()
        insertAllPopular(data)
    }
}
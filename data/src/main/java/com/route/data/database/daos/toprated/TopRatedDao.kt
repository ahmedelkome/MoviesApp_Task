package com.route.data.database.daos.toprated

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.route.domain.models.toprated.TopRated

@Dao
interface TopRatedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopRated(listOfTopRated:List<TopRated>)

    @Query("SELECT * FROM TopRated_Table")
    suspend fun getAllTopRated():List<TopRated>

    @Query("DELETE FROM TopRated_Table WHERE (strftime('%s', 'now') - timestamp) >= :expiryTime")
    suspend fun invalidateCache(expiryTime: Long)

    @Query("DELETE FROM TopRated_Table")
    suspend fun clearList()

    @Transaction
    suspend fun replaceData(data: List<TopRated>) {
        clearList()
        insertAllTopRated(data)
    }
}
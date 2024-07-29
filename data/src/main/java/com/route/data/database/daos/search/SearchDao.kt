package com.route.data.database.daos.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.route.domain.models.search.Search

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSearch(listOfSearch: List<Search>)

    @Query("SELECT * FROM Search_Table WHERE title = :title")
    suspend fun getAllSearch(title: String): List<Search>

    @Query("DELETE FROM Search_Table WHERE (strftime('%s', 'now') - timestamp) >= :expiryTime")
    suspend fun invalidateCache(expiryTime: Long)

    @Query("DELETE FROM Search_Table")
    suspend fun clearList()

    @Query("INSERT OR REPLACE INTO Search_Table (id, timestamp) VALUES (1, :timestamp)")
    suspend fun updateCacheTimestamp(timestamp: Long)

    @Transaction
    suspend fun replaceData(data: List<Search>) {
        clearList()
        insertAllSearch(data)
    }
}
package com.route.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.route.data.database.daos.popular.PopularDao
import com.route.data.database.daos.toprated.TopRatedDao
import com.route.domain.models.popular.Popular
import com.route.domain.models.toprated.TopRated

@Database(entities = [Popular::class, TopRated::class], version = 2)
abstract class MyDataBase : RoomDatabase() {
    abstract fun popularDao(): PopularDao
    abstract fun topRatedDao(): TopRatedDao
}
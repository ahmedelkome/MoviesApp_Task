package com.route.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.route.data.database.daos.popular.PopularDao
import com.route.domain.models.popular.Popular

@Database(entities = [Popular::class], version = 1)
abstract class MyDataBase : RoomDatabase() {
    abstract fun popularDao(): PopularDao
}
package com.route.data.database

import android.content.Context
import androidx.room.Room
import com.route.data.database.daos.popular.PopularDao
import com.route.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DI {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MyDataBase {
        return Room.databaseBuilder(
            context, MyDataBase::class.java,
            Constants.MY_DATA_BASE
        ).build()
    }

    @Provides
    @Singleton
    fun providePopularDao(database: MyDataBase): PopularDao {
        return database.popularDao()
    }
}
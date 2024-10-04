package com.bodakesatish.data.di

import android.app.Application
import androidx.room.Room
import com.bodakesatish.data.source.local.database.TemplateDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    val DATABASE_NAME = "dsd.db"

    @Singleton
    @Provides
    fun providesDatabase(appContext: Application): TemplateDatabase {
        return Room.databaseBuilder(
            appContext,
            TemplateDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesSchemeDao(database: TemplateDatabase) = database.schemeDao()

    @Singleton
    @Provides
    fun providesServicesDao(database: TemplateDatabase) = database.serviceDao()

    @Singleton
    @Provides
    fun providesCustomerDao(database: TemplateDatabase) = database.customerDao()

    @Singleton
    @Provides
    fun providesJobDao(database: TemplateDatabase) = database.jobDao()

    @Singleton
    @Provides
    fun providesJobDetailDao(database: TemplateDatabase) = database.jobDetailDao()
}
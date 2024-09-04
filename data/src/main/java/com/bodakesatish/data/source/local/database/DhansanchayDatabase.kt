package com.bodakesatish.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bodakesatish.data.source.local.convertors.DateConverter
import com.bodakesatish.data.source.local.dao.SchemeDao
import com.bodakesatish.data.source.local.entity.SchemeEntity


@Database(
    entities = [
        SchemeEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class DhansanchayDatabase : RoomDatabase() {
    abstract fun schemeDao(): SchemeDao
}
package com.bodakesatish.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bodakesatish.data.source.local.convertors.DateConverter
import com.bodakesatish.data.source.local.dao.TemplateDao
import com.bodakesatish.data.source.local.entity.TemplateEntity


@Database(
    entities = [
        TemplateEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class TemplateDatabase : RoomDatabase() {
    abstract fun schemeDao(): TemplateDao
}
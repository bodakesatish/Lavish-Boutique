package com.bodakesatish.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bodakesatish.data.source.local.convertors.BooleanConverter
import com.bodakesatish.data.source.local.convertors.DateConverter
import com.bodakesatish.data.source.local.dao.CustomerDao
import com.bodakesatish.data.source.local.dao.JobDao
import com.bodakesatish.data.source.local.dao.JobDetailDao
import com.bodakesatish.data.source.local.dao.ServiceDao
import com.bodakesatish.data.source.local.dao.TemplateDao
import com.bodakesatish.data.source.local.entity.CustomerEntity
import com.bodakesatish.data.source.local.entity.JobDetailEntity
import com.bodakesatish.data.source.local.entity.JobEntity
import com.bodakesatish.data.source.local.entity.ServiceEntity
import com.bodakesatish.data.source.local.entity.TemplateEntity


@Database(
    entities = [
        TemplateEntity::class,
        ServiceEntity::class,
        CustomerEntity::class,
        JobEntity::class,
        JobDetailEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, BooleanConverter::class)
abstract class TemplateDatabase : RoomDatabase() {
    abstract fun schemeDao(): TemplateDao
    abstract fun serviceDao(): ServiceDao
    abstract fun customerDao(): CustomerDao
    abstract fun jobDao(): JobDao
    abstract fun jobDetailDao(): JobDetailDao
}
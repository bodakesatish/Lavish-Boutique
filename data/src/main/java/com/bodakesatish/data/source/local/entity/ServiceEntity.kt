package com.bodakesatish.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.data.source.base.BaseEntity

@Entity(tableName = ServiceEntity.TABLE_NAME)
data class ServiceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Int,
    @ColumnInfo(name = Columns.SERVICE_NAME)
    val serviceName: String,
    @ColumnInfo(name = Columns.SERVICE_DESCRIPTION)
    val serviceDescription: String,
    @ColumnInfo(name = Columns.SERVICE_PRICE)
    val servicePrice: Int
) : BaseEntity() {

    companion object {
        const val TABLE_NAME = "service"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val SERVICE_NAME = "serviceName"
        internal const val SERVICE_DESCRIPTION = "serviceDescription"
        internal const val SERVICE_PRICE = "servicePrice"
    }

}
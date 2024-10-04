package com.bodakesatish.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.data.source.base.BaseEntity

@Entity(tableName = JobDetailEntity.TABLE_NAME)
data class JobDetailEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Int,
    @ColumnInfo(name = Columns.JOB_ID)
    val jobId: Int,
    @ColumnInfo(name = Columns.SERVICE_ID)
    val serviceId: Int,
    @ColumnInfo(name = Columns.SERVICE_PRICE)
    val servicePrice: Int,
    @ColumnInfo(name = Columns.PAID_PRICE)
    val paidPrice: Int,
) : BaseEntity() {

    companion object {
        const val TABLE_NAME = "jobDetail"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val JOB_ID = "jobId"
        internal const val SERVICE_ID = "serviceId"
        internal const val SERVICE_PRICE = "servicePrice"
        internal const val PAID_PRICE = "paidPrice"
    }

}
package com.bodakesatish.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.data.source.base.BaseEntity
import java.util.Date

@Entity(tableName = JobEntity.TABLE_NAME)
data class JobEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Int,
    @ColumnInfo(name = Columns.CUSTOMER_ID)
    val customerId: Int,
    @ColumnInfo(name = Columns.JOB_DATE)
    val jobDate: Date,
    @ColumnInfo(name = Columns.JOB_TOTAL_PRICE)
    val jobTotalPrice: Int,
    @ColumnInfo(name = Columns.PAYMENT_PAID)
    val paymentPaid: Boolean,
    @ColumnInfo(name = Columns.PAYMENT_TYPE)
    val paymentType: String
) : BaseEntity() {

    companion object {
        const val TABLE_NAME = "job"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val CUSTOMER_ID = "customerId"
        internal const val JOB_DATE = "jobDate"
        internal const val JOB_TOTAL_PRICE = "jobTotalPrice"
        internal const val PAYMENT_PAID = "paymentPaid"
        internal const val PAYMENT_TYPE = "paymentType"
    }

}
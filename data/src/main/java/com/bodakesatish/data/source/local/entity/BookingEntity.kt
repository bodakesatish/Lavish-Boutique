package com.bodakesatish.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.data.source.base.BaseEntity

@Entity(tableName = BookingEntity.TABLE_NAME)
data class BookingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Int,
    @ColumnInfo(name = Columns.CUSTOMER_ID)
    val customerId: String,
    @ColumnInfo(name = Columns.BOOKING_TIME)
    val bookingTime: String,
    @ColumnInfo(name = Columns.BOOKING_STATUS)
    val bookingStatus: String
) : BaseEntity() {

    companion object {
        const val TABLE_NAME = "bookings"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val CUSTOMER_ID = "customerId"
        internal const val BOOKING_TIME = "bookingTime"
        internal const val BOOKING_STATUS = "bookingStatus"
    }

}
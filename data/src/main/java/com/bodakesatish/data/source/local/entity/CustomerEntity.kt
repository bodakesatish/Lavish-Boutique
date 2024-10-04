package com.bodakesatish.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.data.source.base.BaseEntity

@Entity(tableName = CustomerEntity.TABLE_NAME)
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Int,
    @ColumnInfo(name = Columns.CUSTOMER_NAME)
    val customerName: String,
    @ColumnInfo(name = Columns.CUSTOMER_PHONE)
    val customerPhone: String
) : BaseEntity() {

    companion object {
        const val TABLE_NAME = "customer"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val CUSTOMER_NAME = "customerName"
        internal const val CUSTOMER_PHONE = "customerPhone"
    }

}
package com.bodakesatish.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.data.source.base.BaseEntity

@Entity(tableName = CategoryTypeEntity.TABLE_NAME)
data class CategoryTypeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Int,
    @ColumnInfo(name = Columns.CATEGORY_ID)
    val categoryId: Int,
    @ColumnInfo(name = Columns.CATEGORY_TYPE_NAME)
    val categoryTypeName: String
) : BaseEntity() {

    companion object {
        const val TABLE_NAME = "categoryProduct"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val CATEGORY_ID = "categoryId"
        internal const val CATEGORY_TYPE_NAME = "categoryTypeName"
    }

}
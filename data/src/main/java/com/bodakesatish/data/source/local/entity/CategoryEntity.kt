package com.bodakesatish.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.data.source.base.BaseEntity

@Entity(tableName = CategoryEntity.TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Int,
    @ColumnInfo(name = Columns.CATEGORY_NAME)
    val categoryName: String,
    @ColumnInfo(name = Columns.CATEGORY_DESCRIPTION)
    val categoryDescription: String
) : BaseEntity() {

    companion object {
        const val TABLE_NAME = "category"
    }

    internal object Columns {
        internal const val ID = "id"
        internal const val CATEGORY_NAME = "categoryName"
        internal const val CATEGORY_DESCRIPTION = "categoryDescription"
    }
}

package com.bodakesatish.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.data.source.local.base.BaseDao
import com.bodakesatish.data.source.local.entity.TemplateEntity

@Dao
interface TemplateDao : BaseDao<TemplateEntity> {

    @Query("SELECT * FROM ${TemplateEntity.TABLE_NAME} LIMIT :pageSize OFFSET :offset")
    suspend fun getTemplateList(pageSize: Int, offset: Int) : List<TemplateEntity>

    @Query("SELECT * FROM ${TemplateEntity.TABLE_NAME} WHERE schemeName LIKE '%' || :searchQuery || '%' LIMIT :pageSize OFFSET :offset")
    suspend fun searchTemplateList(pageSize: Int, offset: Int, searchQuery: String) : List<TemplateEntity>

    @Query("DELETE FROM ${TemplateEntity.TABLE_NAME}")
    suspend fun deleteAllTemplateList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTemplateList(list: List<TemplateEntity>) : List<Long>

}
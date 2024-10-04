package com.bodakesatish.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.data.source.local.base.BaseDao
import com.bodakesatish.data.source.local.entity.ServiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao : BaseDao<ServiceEntity> {

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME} LIMIT :pageSize OFFSET :offset")
    suspend fun getServiceList(pageSize: Int, offset: Int) : List<ServiceEntity>

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME} WHERE ${ServiceEntity.Columns.SERVICE_NAME} LIKE '%' || :searchQuery || '%' LIMIT :pageSize OFFSET :offset")
    suspend fun searchServiceList(pageSize: Int, offset: Int, searchQuery: String) : List<ServiceEntity>

    @Query("DELETE FROM ${ServiceEntity.TABLE_NAME}")
    suspend fun deleteAllServiceList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllServiceList(list: List<ServiceEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(servicesEntity: ServiceEntity) : Long

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME}")
    fun searchServiceListFlow() : Flow<List<ServiceEntity>>

    @Query("SELECT * FROM ${ServiceEntity.TABLE_NAME}")
    suspend fun getServiceList() : List<ServiceEntity>

}
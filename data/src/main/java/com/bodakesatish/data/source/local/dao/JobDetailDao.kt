package com.bodakesatish.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.data.source.local.base.BaseDao
import com.bodakesatish.data.source.local.entity.JobDetailEntity
import com.bodakesatish.data.source.local.entity.ServiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDetailDao : BaseDao<JobDetailEntity> {

    @Query("SELECT * FROM ${JobDetailEntity.TABLE_NAME} LIMIT :pageSize OFFSET :offset")
    suspend fun getJobDetailList(pageSize: Int, offset: Int) : List<JobDetailEntity>

    @Query("SELECT ${ServiceEntity.Columns.SERVICE_NAME} FROM ${JobDetailEntity.TABLE_NAME},${ServiceEntity.TABLE_NAME} WHERE ${JobDetailEntity.Columns.SERVICE_ID} == ${ServiceEntity.TABLE_NAME}.${ServiceEntity.Columns.ID} AND ${JobDetailEntity.Columns.JOB_ID} = :jobId")
    suspend fun getJobDetailList(jobId: Int) : List<String>

    @Query("DELETE FROM ${JobDetailEntity.TABLE_NAME}")
    suspend fun deleteAllJobDetailList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobDetailList(jobDetailEntity: JobDetailEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllJobDetailList(list: List<JobDetailEntity>) : List<Long>

    @Query("SELECT * FROM ${JobDetailEntity.TABLE_NAME}")
    fun searchJobDetailListFlow() : Flow<List<JobDetailEntity>>

}
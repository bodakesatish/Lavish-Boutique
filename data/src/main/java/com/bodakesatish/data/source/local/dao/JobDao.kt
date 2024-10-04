package com.bodakesatish.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.data.source.local.base.BaseDao
import com.bodakesatish.data.source.local.entity.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao : BaseDao<JobEntity> {

    @Query("SELECT * FROM ${JobEntity.TABLE_NAME}")
    suspend fun getJobList() : List<JobEntity>

    @Query("DELETE FROM ${JobEntity.TABLE_NAME}")
    suspend fun deleteAllJobList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllJobList(list: List<JobEntity>) : List<Long>

    @Query("SELECT * FROM ${JobEntity.TABLE_NAME}")
    fun searchJobListFlow() : Flow<List<JobEntity>>

}
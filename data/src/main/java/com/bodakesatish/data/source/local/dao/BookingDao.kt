package com.bodakesatish.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.data.source.local.base.BaseDao
import com.bodakesatish.data.source.local.entity.BookingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao : BaseDao<BookingEntity> {

    @Query("SELECT * FROM ${BookingEntity.TABLE_NAME} LIMIT :pageSize OFFSET :offset")
    suspend fun getTemplateList(pageSize: Int, offset: Int) : List<BookingEntity>

    @Query("DELETE FROM ${BookingEntity.TABLE_NAME}")
    suspend fun deleteAllBookingList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookingList(list: BookingEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBookingList(list: List<BookingEntity>) : List<Long>

    @Query("SELECT * FROM ${BookingEntity.TABLE_NAME}")
    fun searchBookingListFlow() : Flow<List<BookingEntity>>

}
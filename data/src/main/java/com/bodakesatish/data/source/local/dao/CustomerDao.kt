package com.bodakesatish.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.data.source.local.base.BaseDao
import com.bodakesatish.data.source.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao : BaseDao<CustomerEntity> {

    @Query("SELECT * FROM ${CustomerEntity.TABLE_NAME}")
    suspend fun getCustomerList() : List<CustomerEntity>

    @Query("SELECT * FROM ${CustomerEntity.TABLE_NAME} WHERE ${CustomerEntity.Columns.CUSTOMER_NAME} LIKE '%' || :searchQuery || '%' LIMIT :pageSize OFFSET :offset")
    suspend fun searchCustomerList(pageSize: Int, offset: Int, searchQuery: String) : List<CustomerEntity>

    @Query("DELETE FROM ${CustomerEntity.TABLE_NAME}")
    suspend fun deleteAllCustomerList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: CustomerEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCustomerList(list: List<CustomerEntity>) : List<Long>

    @Query("SELECT * FROM ${CustomerEntity.TABLE_NAME}")
    fun searchCustomerListFlow() : Flow<List<CustomerEntity>>

    @Query("SELECT * FROM ${CustomerEntity.TABLE_NAME} WHERE ${CustomerEntity.Columns.ID} = :customerId")
    fun getCustomer(customerId: Int): CustomerEntity

}
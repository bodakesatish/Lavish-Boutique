package com.bodakesatish.data.source.local.source

import android.util.Log
import com.bodakesatish.data.mapper.local.toDomainModel
import com.bodakesatish.data.mapper.local.toLocalCustomerEntity
import com.bodakesatish.data.source.DataSource
import com.bodakesatish.data.source.base.RemoteResponseCode
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.local.dao.CustomerDao
import com.bodakesatish.data.source.local.entity.CustomerEntity
import com.bodakesatish.data.source.local.entity.ServiceEntity
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.model.response.Service
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerDataSourceLocal
@Inject
constructor(
    private val templateDao: CustomerDao
) {

    private val tag = this.javaClass.simpleName

    suspend fun insertOrUpdateCustomer(service: CustomerEntity): Long {
        val serviceId = templateDao.insertCustomer(service)
        return serviceId
    }

    suspend fun deleteAllTemplateList() {
        val data = templateDao.deleteAllCustomerList()
    }

    suspend fun getCustomerList(): List<Customer> {
        Log.i(tag, "In $tag getServicesList")

        val data = templateDao.getCustomerList().map { templateEntityList ->
            templateEntityList.toDomainModel()
        }
        Log.i(tag, "In $tag $data")

        return data
    }

}
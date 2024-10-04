package com.bodakesatish.data.source.local.source

import android.util.Log
import com.bodakesatish.data.mapper.local.toDomainModel
import com.bodakesatish.data.source.local.dao.ServiceDao
import com.bodakesatish.data.source.local.entity.ServiceEntity
import com.bodakesatish.domain.model.response.Service
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServicesDataSourceLocal
@Inject
constructor(
    private val templateDao: ServiceDao
) {

    private val tag = this.javaClass.simpleName

    suspend fun insertOrUpdateService(service: ServiceEntity): Long {
        val serviceId = templateDao.insertService(service)
        return serviceId
    }

    suspend fun deleteAllTemplateList() {
        val data = templateDao.deleteAllServiceList()
    }

    suspend fun getServicesList(): List<Service> {
        Log.i(tag, "In $tag getTemplateListFlow")

        val data = templateDao.getServiceList().map { templateEntityList ->
            templateEntityList.toDomainModel()
        }
        Log.i(tag, "In $tag $data")

        return data
    }

}
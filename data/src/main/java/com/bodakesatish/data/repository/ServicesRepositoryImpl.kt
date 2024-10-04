package com.bodakesatish.data.repository

import com.bodakesatish.data.mapper.local.toLocal
import com.bodakesatish.data.mapper.local.toServiceLocal
import com.bodakesatish.data.source.local.source.ServicesDataSourceLocal
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.domain.repository.ServicesRepository
import com.bodakesatish.domain.usecases.CreateOrEditServiceUseCase
import com.bodakesatish.domain.usecases.GetServiceListUseCase
import javax.inject.Inject

class ServicesRepositoryImpl
@Inject
constructor(
    private val localSourceServices: ServicesDataSourceLocal
) : ServicesRepository {

    private val tag = this.javaClass.simpleName

    override suspend fun getServicesList(): GetServiceListUseCase.Response {
        val localOutput = localSourceServices.getServicesList()
        val response = GetServiceListUseCase.Response()
        response.setResponseCode(ResponseCode.Success)
        response.setData(localOutput)
        return response
    }

    override suspend fun createOrEditService(requestModel: Service): CreateOrEditServiceUseCase.Response {
       val localOutput = localSourceServices.insertOrUpdateService(requestModel.toServiceLocal())
        val response = CreateOrEditServiceUseCase.Response()
        response.setResponseCode(ResponseCode.Success)
        response.setData(localOutput)
        return response
    }


}
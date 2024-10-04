package com.bodakesatish.data.repository

import com.bodakesatish.data.mapper.local.toLocal
import com.bodakesatish.data.source.local.source.CustomerDataSourceLocal
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.repository.CustomerRepository
import com.bodakesatish.domain.usecases.CreateOrEditCustomerUseCase
import com.bodakesatish.domain.usecases.GetCustomerListUseCase
import javax.inject.Inject

class CustomerRepositoryImpl
@Inject
constructor(
    private val localSourceServices: CustomerDataSourceLocal
) : CustomerRepository {

    private val tag = this.javaClass.simpleName

    override suspend fun getCustomerList(): GetCustomerListUseCase.Response {
        val localOutput = localSourceServices.getCustomerList()
        val response = GetCustomerListUseCase.Response()
        response.setResponseCode(ResponseCode.Success)
        response.setData(localOutput)
        return response
    }

    override suspend fun createOrEditCustomer(requestModel: Customer): CreateOrEditCustomerUseCase.Response {
       val localOutput = localSourceServices.insertOrUpdateCustomer(requestModel.toLocal())
        val response = CreateOrEditCustomerUseCase.Response()
        response.setResponseCode(ResponseCode.Success)
        response.setData(localOutput)
        return response
    }


}
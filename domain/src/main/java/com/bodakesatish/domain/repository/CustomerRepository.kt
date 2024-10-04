package com.bodakesatish.domain.repository

import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.usecases.CreateOrEditCustomerUseCase
import com.bodakesatish.domain.usecases.GetCustomerListUseCase

interface CustomerRepository {
    suspend fun getCustomerList(): GetCustomerListUseCase.Response
    suspend fun createOrEditCustomer(requestModel: Customer): CreateOrEditCustomerUseCase.Response
}
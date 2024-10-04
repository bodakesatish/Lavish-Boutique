package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.repository.CustomerRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class CreateOrEditCustomerUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) :
    BaseUseCase<CreateOrEditCustomerUseCase.Request, CreateOrEditCustomerUseCase.Response, Customer, Long>() {

    override suspend fun buildUseCase(request: Request): Response {
        return customerRepository.createOrEditCustomer(request.getRequestModel())
    }

    class Request : BaseUseCase.Request<Customer>()

    class Response : BaseUseCase.Response<Long>()

}
package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.repository.CustomerRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class GetCustomerListUseCase @Inject constructor(
    private val customerRepository: CustomerRepository
) :
    BaseUseCase<GetCustomerListUseCase.Request, GetCustomerListUseCase.Response, Any, List<Customer>>() {

    override suspend fun buildUseCase(request: Request): Response {
        return customerRepository.getCustomerList()
    }

    class Request : BaseUseCase.Request<Any>()

    class Response : BaseUseCase.Response<List<Customer>>()

}
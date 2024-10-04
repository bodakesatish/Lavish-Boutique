package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.response.Bill
import com.bodakesatish.domain.repository.BillRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class GetBillListUseCase @Inject constructor(
    private val customerRepository: BillRepository
) :
    BaseUseCase<GetBillListUseCase.Request, GetBillListUseCase.Response, Any, List<Bill>>() {

    override suspend fun buildUseCase(request: Request): Response {
        return customerRepository.getJobList()
    }

    class Request : BaseUseCase.Request<Any>()

    class Response : BaseUseCase.Response<List<Bill>>()

}
package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.response.Bill
import com.bodakesatish.domain.repository.BillRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class CreateOrEditBillUseCase @Inject constructor(
    private val customerRepository: BillRepository
) :
    BaseUseCase<CreateOrEditBillUseCase.Request, CreateOrEditBillUseCase.Response, Bill, Int>() {

    override suspend fun buildUseCase(request: Request): Response {
        return customerRepository.createJob(request.getRequestModel())
    }

    class Request : BaseUseCase.Request<Bill>()

    class Response : BaseUseCase.Response<Int>()

}
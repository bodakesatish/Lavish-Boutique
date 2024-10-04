package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.domain.repository.ServicesRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class GetServiceListUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) :
    BaseUseCase<GetServiceListUseCase.Request, GetServiceListUseCase.Response, Any, List<Service>>() {

    override suspend fun buildUseCase(request: Request): Response {
        return servicesRepository.getServicesList()
    }

    class Request : BaseUseCase.Request<Any>()

    class Response : BaseUseCase.Response<List<Service>>()

}
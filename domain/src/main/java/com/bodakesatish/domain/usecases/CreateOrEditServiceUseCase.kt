package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.domain.repository.ServicesRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class CreateOrEditServiceUseCase @Inject constructor(
    private val servicesRepository: ServicesRepository
) :
    BaseUseCase<CreateOrEditServiceUseCase.Request, CreateOrEditServiceUseCase.Response, Service, Long>() {

    override suspend fun buildUseCase(request: Request): Response {
        return servicesRepository.createOrEditService(request.getRequestModel())
    }

    class Request : BaseUseCase.Request<Service>()

    class Response : BaseUseCase.Response<Long>()

}
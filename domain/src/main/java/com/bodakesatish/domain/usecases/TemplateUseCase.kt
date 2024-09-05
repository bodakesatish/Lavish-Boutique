package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.model.response.Template
import com.bodakesatish.domain.repository.TemplateRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class TemplateUseCase @Inject constructor(
    private val templateRepository: TemplateRepository
) :
BaseUseCase<TemplateUseCase.Request, TemplateUseCase.Response, TemplateRequest, List<Template>>(){

    override suspend fun buildUseCase(request: Request): Response {
        return templateRepository.getTemplateList(request.getRequestModel())
    }

    class Request : BaseUseCase.Request<TemplateRequest>()

    class Response : BaseUseCase.Response<List<Template>>()

}
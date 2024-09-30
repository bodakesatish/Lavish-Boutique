package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.model.response.Template
import com.bodakesatish.domain.repository.TemplateRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TemplateFlowUseCase @Inject constructor(
    private val templateRepository: TemplateRepository
) :
BaseUseCase<TemplateFlowUseCase.Request, TemplateFlowUseCase.Response, TemplateRequest, Flow<List<Template>>>(){

    override suspend fun buildUseCase(request: Request): Response {
        return templateRepository.getTemplateListFlow(request.getRequestModel())
    }

    class Request : BaseUseCase.Request<TemplateRequest>()

    class Response : BaseUseCase.Response<Flow<List<Template>>>()

}
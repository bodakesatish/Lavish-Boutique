package com.bodakesatish.domain.usecases

import com.bodakesatish.domain.model.request.SchemeRequest
import com.bodakesatish.domain.model.response.SchemeModel
import com.bodakesatish.domain.repository.SchemeRepository
import com.bodakesatish.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class SchemeListUseCase @Inject constructor(
    private val schemeRepository: SchemeRepository
) :
BaseUseCase<SchemeListUseCase.Request, SchemeListUseCase.Response, SchemeRequest, List<SchemeModel>>(){

    override suspend fun buildUseCase(request: Request): Response {
        return schemeRepository.getSchemeList(request.getRequestModel().currentPage, request.getRequestModel().pageSize)
    }

    class Request : BaseUseCase.Request<SchemeRequest>()

    class Response : BaseUseCase.Response<List<SchemeModel>>()

}
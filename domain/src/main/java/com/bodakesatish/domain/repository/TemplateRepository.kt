package com.bodakesatish.domain.repository

import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.usecases.TemplateUseCase

interface TemplateRepository {
    suspend fun getTemplateList(request: TemplateRequest): TemplateUseCase.Response
}
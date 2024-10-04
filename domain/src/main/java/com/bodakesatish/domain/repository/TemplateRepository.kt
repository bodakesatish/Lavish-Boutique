package com.bodakesatish.domain.repository

import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.domain.model.response.Template
import com.bodakesatish.domain.usecases.CreateOrEditServiceUseCase
import com.bodakesatish.domain.usecases.TemplateFlowUseCase
import com.bodakesatish.domain.usecases.TemplateUseCase
import kotlinx.coroutines.flow.Flow

interface TemplateRepository {
    suspend fun getTemplateList(request: TemplateRequest): TemplateUseCase.Response
    suspend fun getTemplateListFlow(request: TemplateRequest): TemplateFlowUseCase.Response
}
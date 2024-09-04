package com.bodakesatish.domain.repository

import com.bodakesatish.domain.usecases.TemplateUseCase

interface TemplateRepository {
    suspend fun getTemplateList(currentPage: Int, pageSize: Int): TemplateUseCase.Response
}
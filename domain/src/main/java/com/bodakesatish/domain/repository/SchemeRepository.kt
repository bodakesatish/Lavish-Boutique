package com.bodakesatish.domain.repository

import com.bodakesatish.domain.usecases.SchemeListUseCase

interface SchemeRepository {
    suspend fun getSchemeList(currentPage: Int, pageSize: Int): SchemeListUseCase.Response
}
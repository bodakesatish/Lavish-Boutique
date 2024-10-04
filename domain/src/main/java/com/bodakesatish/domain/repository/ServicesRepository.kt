package com.bodakesatish.domain.repository

import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.domain.usecases.CreateOrEditServiceUseCase
import com.bodakesatish.domain.usecases.GetServiceListUseCase

interface ServicesRepository {
    suspend fun getServicesList(): GetServiceListUseCase.Response
    suspend fun createOrEditService(requestModel: Service): CreateOrEditServiceUseCase.Response
}
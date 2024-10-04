package com.bodakesatish.domain.repository

import com.bodakesatish.domain.model.response.Bill
import com.bodakesatish.domain.usecases.CreateOrEditBillUseCase
import com.bodakesatish.domain.usecases.GetBillListUseCase

interface BillRepository {
    suspend fun getJobList(): GetBillListUseCase.Response
    suspend fun createJob(requestModel: Bill): CreateOrEditBillUseCase.Response
}
package com.bodakesatish.data.repository

import com.bodakesatish.data.mapper.local.toJobLocal
import com.bodakesatish.data.mapper.local.toNewJobDetailLocal
import com.bodakesatish.data.source.local.source.JobDataSourceLocal
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.response.Bill
import com.bodakesatish.domain.repository.BillRepository
import com.bodakesatish.domain.usecases.CreateOrEditBillUseCase
import com.bodakesatish.domain.usecases.GetBillListUseCase
import javax.inject.Inject

class JobRepositoryImpl
@Inject
constructor(
    private val localSourceServices: JobDataSourceLocal
) : BillRepository {

    private val tag = this.javaClass.simpleName

    override suspend fun getJobList(): GetBillListUseCase.Response {
        val localOutput = localSourceServices.getJobList()
        val response = GetBillListUseCase.Response()
        response.setResponseCode(ResponseCode.Success)
        response.setData(localOutput)
        return response
    }

    override suspend fun createJob(requestModel: Bill): CreateOrEditBillUseCase.Response {
        val jobId = localSourceServices.insertJob(requestModel.toJobLocal())
        val jobDetailList = requestModel.jobDetailList.map {
            it.toNewJobDetailLocal(jobId)
        }
        localSourceServices.insertJobDetail(jobDetailList)
        val response = CreateOrEditBillUseCase.Response()
        response.setResponseCode(ResponseCode.Success)
        response.setData(jobId)
        return response
    }


}
package com.bodakesatish.data.repository

import android.util.Log
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.local.source.TemplateDataSourceLocal
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.repository.TemplateRepository
import com.bodakesatish.domain.usecases.TemplateFlowUseCase
import com.bodakesatish.domain.usecases.TemplateUseCase
import javax.inject.Inject

class TemplateRepositoryImpl
@Inject
constructor(
    private val templateDataSourceLocal: TemplateDataSourceLocal
) : TemplateRepository {

    private val tag = this.javaClass.simpleName

    override suspend fun getTemplateList(
        request: TemplateRequest
    ): TemplateUseCase.Response {

        Log.i(tag, "In $tag getTemplateList")

        val response = TemplateUseCase.Response()
        response.setResponseCode(ResponseCode.Success)
        val localOutput = templateDataSourceLocal.getTemplateList(
            request.pageSize,
            request.currentPage,
            searchQuery = request.searchText
        )
        val responseData = if (localOutput is BaseOutput.Success) {
            localOutput.output!!
        } else {
            ArrayList()
        }
        if (responseData.isNotEmpty()) {
            response.setData(responseData)
            return response
        }


        return response
    }

    override suspend fun getTemplateListFlow(
        request: TemplateRequest
    ): TemplateFlowUseCase.Response {
        Log.i(tag, "In $tag getTemplateListFlow")
        val response = TemplateFlowUseCase.Response()
        val localOutput = templateDataSourceLocal.getTemplateListFlow(request.searchText)
        val d = localOutput as BaseOutput.Success
        response.setResponseCode(ResponseCode.Success)
        response.setData(d.output)
        return response
    }
}
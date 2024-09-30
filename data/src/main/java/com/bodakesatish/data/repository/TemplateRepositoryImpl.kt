package com.bodakesatish.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.bodakesatish.data.prefs.SessionConstants
import com.bodakesatish.data.prefs.SessionManager
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.local.source.TemplateDataSourceLocal
import com.bodakesatish.data.source.remote.source.TemplateDataSourceRemote
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.repository.TemplateRepository
import com.bodakesatish.domain.usecases.TemplateFlowUseCase
import com.bodakesatish.domain.usecases.TemplateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.util.Date
import javax.inject.Inject

class TemplateRepositoryImpl
@Inject
constructor(
    private val sessionManager: SessionManager,
    private val templateDataSourceLocal: TemplateDataSourceLocal,
    private val templateDataSourceRemote: TemplateDataSourceRemote
) : TemplateRepository {

    private val tag = this.javaClass.simpleName

    override suspend fun getTemplateList(
        request: TemplateRequest
    ): TemplateUseCase.Response {

        Log.i(tag, "In $tag getTemplateList")

        val response = TemplateUseCase.Response()

        val THIRTY_SECONDS_IN_MILLIS =30000L // 30 seconds * 1000 milliseconds/second
        val ONE_HOUR_IN_MILLIS = 3600000L // 60 minutes * 60 seconds * 1000 milliseconds
        val sessionSyncDateTime = sessionManager.getLong(SessionConstants.SESSION_LAST_SYNC_DATE,0L)
        Log.i(tag, "In $tag getTemplateList sessionSyncDateTime -> $sessionSyncDateTime")
        val currentDateTime = Date().time
        Log.i(tag, "In $tag getTemplateList currentDateTime -> $currentDateTime")
        val difference = currentDateTime - sessionSyncDateTime
        Log.i(tag, "In $tag getTemplateList difference -> $difference")

        if(difference < ONE_HOUR_IN_MILLIS) {
            response.setResponseCode(ResponseCode.Success)
            val localOutput = templateDataSourceLocal.getTemplateList(request.pageSize, request.currentPage, searchQuery = request.searchText)
            val responseData = if (localOutput is BaseOutput.Success) {
                localOutput.output!!
            } else {
                ArrayList()
            }
            if(responseData.isNotEmpty()) {
                response.setData(responseData)
                return response
            }

        }

        val remoteOutput = templateDataSourceRemote.getTemplateList(request.pageSize, request.currentPage, searchQuery = request.searchText)
        if(remoteOutput is BaseOutput.Success) {
            response.setResponseCode(ResponseCode.Success)
            templateDataSourceLocal.deleteAllTemplateList()
            remoteOutput.output?.let {
                templateDataSourceLocal.insertAllTemplateList(it)
                val localOutput = templateDataSourceLocal.getTemplateList(request.pageSize, request.currentPage, searchQuery = request.searchText)
                val responseData = if (localOutput is BaseOutput.Success) {
                    localOutput.output!!
                } else {
                    ArrayList()
                }
                sessionManager.set(SessionConstants.SESSION_LAST_SYNC_DATE,Date().time)
                response.setData(responseData)
            }
        } else {
            response.setResponseCode(ResponseCode.Fail)
        }

        return response
    }

    override suspend fun getTemplateListFlow(
        request: TemplateRequest
    ) : TemplateFlowUseCase.Response {
        Log.i(tag, "In $tag getTemplateListFlow")
        val response = TemplateFlowUseCase.Response()
        val localOutput = templateDataSourceLocal.getTemplateListFlow(request.searchText)
        val d = localOutput as BaseOutput.Success
        response.setResponseCode(ResponseCode.Success)
        response.setData(d.output)
        return response
    }
}
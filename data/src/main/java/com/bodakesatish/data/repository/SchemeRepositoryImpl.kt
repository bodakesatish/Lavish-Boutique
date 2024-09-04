package com.bodakesatish.data.repository

import android.util.Log
import com.bodakesatish.data.prefs.SessionManager
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.local.source.SchemeDataSourceLocal
import com.bodakesatish.data.source.remote.source.SchemeDataSourceRemote
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.repository.SchemeRepository
import com.bodakesatish.domain.usecases.SchemeListUseCase
import javax.inject.Inject

class SchemeRepositoryImpl
@Inject
constructor(
    private val sessionManager: SessionManager,
    private val remoteDataSource: SchemeDataSourceRemote,
    private val localDataSource: SchemeDataSourceLocal,
) : SchemeRepository {

    private val tag = this.javaClass.simpleName

    override suspend fun getSchemeList(
        currentPage: Int,
        pageSize: Int
    ): SchemeListUseCase.Response {

        Log.i(tag, "In $tag getPaginatedSchemeList")
        val response = SchemeListUseCase.Response()
        response.setResponseCode(ResponseCode.Success)
        val localOutput = localDataSource.getPaginatedSchemeList(currentPage = currentPage, pageSize = pageSize)
        val responseData = if (localOutput is BaseOutput.Success) {
            localOutput.output!!
        } else {
            ArrayList()
        }
        response.setData(responseData)
        return response

    }
}
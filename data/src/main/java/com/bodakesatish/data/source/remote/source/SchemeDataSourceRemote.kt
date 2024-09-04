package com.bodakesatish.data.source.remote.source

import com.bodakesatish.data.source.DataSource
import com.bodakesatish.domain.model.base.BaseRequest
import com.bodakesatish.data.source.base.ApiResponseCode
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.remote.source.base.BaseDataSourceRemote
import com.bodakesatish.domain.model.response.SchemeModel
import com.bodakesatish.data.mapper.remote.SchemeRemoteMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeDataSourceRemote
@Inject
constructor(
    private val apiService: ApiService,
    private val schemeRemoteMapper: SchemeRemoteMapper
) : BaseDataSourceRemote<BaseRequest>(),
DataSource.SchemeSource {

    override suspend fun getSchemeList(): BaseOutput<List<SchemeModel>> {
        val response = sendRequest { apiService.schemeList() }
        return getOutput(response) { schemeRemoteMapper.map(response.body()!!) }
    }

    override suspend fun getSchemeListCount(): BaseOutput<Int> {
        return BaseOutput.Success(ApiResponseCode.SUCCESS, 0)
    }

    override suspend fun getPaginatedSchemeList(
        page: Int,
        pageSize: Int
    ): BaseOutput<List<SchemeModel>> {
        return BaseOutput.Success(ApiResponseCode.SUCCESS, emptyList())
    }


}
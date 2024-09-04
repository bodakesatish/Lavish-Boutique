package com.bodakesatish.data.source.local.source

import com.bodakesatish.data.mapper.local.SchemeLocalMapper
import com.bodakesatish.data.source.DataSource
import com.bodakesatish.data.source.base.ApiResponseCode
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.local.dao.SchemeDao
import com.bodakesatish.domain.model.response.SchemeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeDataSourceLocal
@Inject
constructor(
    private val schemeDao: SchemeDao,
    private val schemeLocalMapper: SchemeLocalMapper
) : DataSource.SchemeSource {

    override suspend fun getSchemeList(): BaseOutput<List<SchemeModel>> {
        val data = schemeDao.getSchemeList()
        return BaseOutput.Success(ApiResponseCode.SUCCESS, schemeLocalMapper.map(data))
    }

    override suspend fun getSchemeListCount(): BaseOutput<Int> {
        val data = schemeDao.getSchemeListCount()
        return BaseOutput.Success(ApiResponseCode.SUCCESS, data)
    }

    override suspend fun getPaginatedSchemeList(
        currentPage: Int,
        pageSize: Int
    ): BaseOutput<List<SchemeModel>> {
        val offset = currentPage * pageSize
        val data = schemeDao.getPaginatedSchemeList(limit = pageSize, offset = offset)
        return BaseOutput.Success(ApiResponseCode.SUCCESS,schemeLocalMapper.map(data))
    }

}
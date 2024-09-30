package com.bodakesatish.data.source.local.source

import android.util.Log
import com.bodakesatish.data.mapper.local.TemplateLocalMapper
import com.bodakesatish.data.source.DataSource
import com.bodakesatish.data.source.base.RemoteResponseCode
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.local.dao.TemplateDao
import com.bodakesatish.domain.model.response.Template
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateDataSourceLocal
@Inject
constructor(
    private val templateDao: TemplateDao,
    private val templateLocalMapper: TemplateLocalMapper
) : DataSource.TemplateSource {

    private val tag = this.javaClass.simpleName

    override suspend fun getTemplateList(
        pageSize: Int,
        offset: Int,
        searchQuery: String
    ): BaseOutput<List<Template>> {
        val data = if (searchQuery.isEmpty()) templateDao.getTemplateList(pageSize, offset)
        else templateDao.searchTemplateList(pageSize, offset, searchQuery)
        return BaseOutput.Success(RemoteResponseCode.SUCCESS, templateLocalMapper.map(data))
    }

    suspend fun insertAllTemplateList(list: List<Template>) {
        val data = templateDao.insertAllTemplateList(templateLocalMapper.reverse(list))
    }

    suspend fun deleteAllTemplateList() {
        val data = templateDao.deleteAllTemplateList()
    }

    suspend fun getTemplateListFlow(searchQuery: String): BaseOutput<Flow<List<Template>>> {
        Log.i(tag, "In $tag getTemplateListFlow")

        val data = templateDao.searchTemplateListFlow().map { templateEntityList ->
            templateLocalMapper.map(templateEntityList)
        }
        Log.i(tag, "In $tag $data")

        return if (data.firstOrNull()?.isEmpty() == true) {
            BaseOutput.Success(RemoteResponseCode.EMPTY, data)
        } else {
            BaseOutput.Success(RemoteResponseCode.SUCCESS, data)
        }

    }

}
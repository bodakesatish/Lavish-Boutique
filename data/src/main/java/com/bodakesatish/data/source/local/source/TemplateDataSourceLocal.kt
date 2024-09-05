package com.bodakesatish.data.source.local.source

import com.bodakesatish.data.mapper.local.TemplateLocalMapper
import com.bodakesatish.data.source.DataSource
import com.bodakesatish.data.source.base.RemoteResponseCode
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.local.dao.TemplateDao
import com.bodakesatish.domain.model.response.Template
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateDataSourceLocal
@Inject
constructor(
    private val templateDao: TemplateDao,
    private val templateLocalMapper: TemplateLocalMapper
) : DataSource.TemplateSource {

    override suspend fun getTemplateList(pageSize: Int, offset: Int, searchQuery: String): BaseOutput<List<Template>> {
        val data =  if(searchQuery.isEmpty()) templateDao.getTemplateList(pageSize, offset)
            else templateDao.searchTemplateList(pageSize, offset, searchQuery)
        return BaseOutput.Success(RemoteResponseCode.SUCCESS, templateLocalMapper.map(data))
    }

    suspend fun insertAllTemplateList(list: List<Template>) {
        val data = templateDao.insertAllTemplateList(templateLocalMapper.reverse(list))
    }

    suspend fun deleteAllTemplateList() {
        val data = templateDao.deleteAllTemplateList()
    }

}
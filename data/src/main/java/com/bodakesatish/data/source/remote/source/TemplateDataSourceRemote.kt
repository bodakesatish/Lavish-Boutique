package com.bodakesatish.data.source.remote.source

import com.bodakesatish.data.source.DataSource
import com.bodakesatish.domain.model.base.BaseRequest
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.remote.source.base.BaseDataSourceRemote
import com.bodakesatish.domain.model.response.Template
import com.bodakesatish.data.mapper.remote.TemplateRemoteMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateDataSourceRemote
@Inject
constructor(
    private val templateApiService: TemplateApiService,
    private val templateRemoteMapper: TemplateRemoteMapper
) : BaseDataSourceRemote<BaseRequest>(),
DataSource.TemplateSource {

    override suspend fun getTemplateList(pageSize: Int, offset: Int, searchQuery: String): BaseOutput<List<Template>> {

        val response  = if(searchQuery.isEmpty()) {
             sendRequest { templateApiService.templateList() }
        } else {
             sendRequest { templateApiService.searchTemplateList(query = searchQuery) }
        }
        return getOutput(response) { templateRemoteMapper.map(response.body()!!) }
    }

}
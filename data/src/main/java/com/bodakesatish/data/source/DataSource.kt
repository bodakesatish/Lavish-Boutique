package com.bodakesatish.data.source

import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.domain.model.response.Template

interface DataSource {

    interface TemplateSource {
        suspend fun getTemplateList(pageSize: Int, offset: Int, searchQuery: String): BaseOutput<List<Template>>
    }
}
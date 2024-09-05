package com.bodakesatish.data.mapper.remote

import com.bodakesatish.data.mapper.base.BaseMapper
import com.bodakesatish.data.source.remote.dto.TemplateDTO
import com.bodakesatish.domain.model.response.Template
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateRemoteMapper
@Inject constructor() : BaseMapper<Template, TemplateDTO>(){

    override fun map(entity: TemplateDTO): Template {
        return Template(
            0,
            entity.schemeCode,
            entity.schemeName
        )
    }

}
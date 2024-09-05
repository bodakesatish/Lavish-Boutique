package com.bodakesatish.data.mapper.local

import com.bodakesatish.data.mapper.base.BaseMapper
import com.bodakesatish.data.source.local.entity.TemplateEntity
import com.bodakesatish.domain.model.response.Template
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateLocalMapper
@Inject constructor() : BaseMapper<Template, TemplateEntity>(){

    override fun map(entity: TemplateEntity): Template {
        return Template(
            entity.id,
            entity.schemeCode,
            entity.schemeName
        )
    }

    override fun reverse(model: Template): TemplateEntity {
        return TemplateEntity(
            0,
            model.schemeCode,
            model.schemeName
        )
    }
}
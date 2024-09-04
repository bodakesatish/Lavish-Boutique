package com.bodakesatish.data.mapper.local

import com.bodakesatish.data.mapper.base.BaseMapper
import com.bodakesatish.data.source.local.entity.SchemeEntity
import com.bodakesatish.domain.model.response.SchemeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeLocalMapper
@Inject constructor() : BaseMapper<SchemeModel, SchemeEntity>(){

    override fun map(entity: SchemeEntity): SchemeModel {
        return SchemeModel(
            entity.id,
            entity.schemeCode,
            entity.schemeName
        )
    }

    override fun reverse(model: SchemeModel): SchemeEntity {
        return SchemeEntity(
            0,
            model.schemeCode,
            model.schemeName
        )
    }
}
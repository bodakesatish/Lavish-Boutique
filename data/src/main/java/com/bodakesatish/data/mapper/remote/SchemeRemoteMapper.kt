package com.bodakesatish.data.mapper.remote

import com.bodakesatish.data.mapper.base.BaseMapper
import com.bodakesatish.data.source.remote.entity.SchemeResponse
import com.bodakesatish.domain.model.response.SchemeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeRemoteMapper
@Inject constructor() : BaseMapper<SchemeModel, SchemeResponse>(){

    override fun map(entity: SchemeResponse): SchemeModel {
        return SchemeModel(
            0,
            entity.schemeCode,
            entity.schemeName
        )
    }

}
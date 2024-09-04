package com.bodakesatish.data.source.remote.entity

import com.bodakesatish.data.source.base.BaseEntity

data class SchemeResponse(
    val schemeCode: String,
    val schemeName: String
) : BaseEntity()
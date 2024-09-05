package com.bodakesatish.data.source.remote.dto

import com.bodakesatish.data.source.base.BaseEntity

data class TemplateDTO(
    val schemeCode: String,
    val schemeName: String
) : BaseEntity()
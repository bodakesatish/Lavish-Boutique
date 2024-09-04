package com.bodakesatish.domain.model.response

import com.bodakesatish.domain.model.base.BaseResponse

data class TemplateModel(
    val id: Int,
    val schemeCode: String,
    val schemeName: String
) : BaseResponse
package com.bodakesatish.domain.model.request

import com.bodakesatish.domain.model.base.BaseRequest

data class TemplateRequest(
    var currentPage: Int,
    var pageSize: Int,
    val searchText: String) : BaseRequest

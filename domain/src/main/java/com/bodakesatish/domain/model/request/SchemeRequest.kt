package com.bodakesatish.domain.model.request

import com.bodakesatish.domain.model.base.BaseRequest

data class SchemeRequest(val currentPage: Int, val pageSize: Int) : BaseRequest

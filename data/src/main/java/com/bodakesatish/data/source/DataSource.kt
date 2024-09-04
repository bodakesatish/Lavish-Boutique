package com.bodakesatish.data.source

import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.domain.model.response.SchemeModel

interface DataSource {

    interface SchemeSource {
        suspend fun getSchemeList(): BaseOutput<List<SchemeModel>>
        suspend fun getSchemeListCount() : BaseOutput<Int>
        suspend fun getPaginatedSchemeList(currentPage: Int, pageSize: Int): BaseOutput<List<SchemeModel>>
    }
}
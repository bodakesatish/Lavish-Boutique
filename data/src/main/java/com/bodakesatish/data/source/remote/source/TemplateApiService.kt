package com.bodakesatish.data.source.remote.source

import com.bodakesatish.data.source.remote.dto.TemplateDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TemplateApiService {

    @GET("mf")
    suspend fun templateList() : Response<List<TemplateDTO>>

    @GET("mf/search")
    suspend fun searchTemplateList(@Query("q") query: String) : Response<List<TemplateDTO>>

}
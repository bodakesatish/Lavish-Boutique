package com.bodakesatish.domain.model.response

data class Service(
    val id: Int,
    val serviceName: String,
    val serviceDescription: String,
    val servicePrice: Int
)
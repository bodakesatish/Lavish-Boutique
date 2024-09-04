package com.bodakesatish.domain.model

sealed class ResponseCode {
    data class Success(val message: String? = null) : ResponseCode()
    data class Empty(val message: String? = null) : ResponseCode()
    data class Network(val message: String? = null) : ResponseCode()
    data class Authentication(val message: String? = null) : ResponseCode()
    data class Fail(val message: String? = null) : ResponseCode()
    data class NOT_FOUND(val message: String? = null) : ResponseCode()
    data class BAD_REQUEST(val message: String? = null) : ResponseCode()
}
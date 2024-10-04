package com.bodakesatish.domain.model.response

data class Booking(
    val id: Int = 0,
    val customerId: String,
    val bookingTime: String,
    val bookingStatus: String
)
package com.bodakesatish.domain.model.response

import java.util.Date

data class Bill(
    val id: Int = 0,
    val customerId: Int = 0,
    var customerName: String = "",
    var serviceList : String = "",
    val jobDate: Date = Date(),
    val jobTotalPrice: Int = 0,
    val paymentPaid: Boolean = false,
    val paymentType: String = "",
    var jobDetailList: List<BillDetail> = emptyList()
)
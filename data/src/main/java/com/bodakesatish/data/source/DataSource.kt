package com.bodakesatish.data.source

import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.domain.model.response.Booking
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.model.response.Template

interface DataSource {

    interface TemplateSource {
        suspend fun getTemplateList(pageSize: Int, offset: Int, searchQuery: String): BaseOutput<List<Template>>
    }

    interface CustomerSource {
        suspend fun getCustomerList(pageSize: Int, offset: Int, searchQuery: String): BaseOutput<List<Customer>>
    }

    interface BookingSource {
        suspend fun getBookingList(pageSize: Int, offset: Int, searchQuery: String): BaseOutput<List<Booking>>
    }
}
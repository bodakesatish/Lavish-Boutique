package com.bodakesatish.data.mapper.local

import com.bodakesatish.data.source.local.entity.BookingEntity
import com.bodakesatish.data.source.local.entity.CustomerEntity
import com.bodakesatish.data.source.local.entity.JobDetailEntity
import com.bodakesatish.data.source.local.entity.JobEntity
import com.bodakesatish.data.source.local.entity.ServiceEntity
import com.bodakesatish.domain.model.response.Booking
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.model.response.Bill
import com.bodakesatish.domain.model.response.BillDetail
import com.bodakesatish.domain.model.response.Service

fun Customer.toLocal() = CustomerEntity(
    id = id,
    customerName = customerName,
    customerPhone = customerPhone
)

@JvmName("toLocalCustomerEntity")
fun List<Customer>.toLocalCustomerEntity() = map(Customer::toLocal)

fun CustomerEntity.toDomainModel() = Customer(
    id = id,
    customerName = customerName,
    customerPhone = customerPhone
)

@JvmName("localCustomerToExternal")
fun List<CustomerEntity>.toDomainModel() = map(CustomerEntity::toDomainModel)

fun Booking.toLocal() = BookingEntity(
    id = id,
    customerId = customerId,
    bookingTime = bookingTime,
    bookingStatus = bookingStatus
)

@JvmName("toLocalBookingEntity")
fun List<Booking>.toLocalBookingEntity() = map(Booking::toLocal)

fun BookingEntity.toDomainModel() = Booking(
    id = id,
    customerId = customerId,
    bookingTime = bookingTime,
    bookingStatus = bookingStatus
)

@JvmName("localBookingToExternal")
fun List<BookingEntity>.toDomainModel() = map(BookingEntity::toDomainModel)

fun Service.toServiceLocal() = ServiceEntity(
    id = id,
    serviceName = serviceName,
    serviceDescription = serviceDescription,
    servicePrice = servicePrice
)

@JvmName("toServiceLocalEntity")
fun List<Service>.toServiceLocalEntity() = map(Service::toServiceLocal)

fun ServiceEntity.toDomainModel() = Service(
    id = id,
    serviceName = serviceName,
    serviceDescription = serviceDescription,
    servicePrice = servicePrice
)

//@JvmName("localToExternal")
//fun List<ServiceEntity>.toDomainModel() = map(ServiceEntity::toDomainModel)

fun Bill.toJobLocal() = JobEntity(
    id = id,
    customerId = customerId,
    jobDate = jobDate,
    jobTotalPrice = jobTotalPrice,
    paymentPaid = paymentPaid,
    paymentType = paymentType
)

@JvmName("toJobLocalEntity")
fun List<Bill>.toServiceLocalEntity() = map(Bill::toJobLocal)

fun JobEntity.toDomainModel() = Bill(
    id = id,
    customerId = customerId,
    jobDate = jobDate,
    jobTotalPrice = jobTotalPrice,
    paymentPaid = paymentPaid,
    paymentType = paymentType
)

@JvmName("jobLocalToExternal")
fun List<JobEntity>.toDomainModel() = map(JobEntity::toDomainModel)

fun BillDetail.toJobDetailLocal() = JobDetailEntity(
    id = id,
    jobId = jobId,
    serviceId = serviceId,
    servicePrice = servicePrice,
    paidPrice = paidPrice
)

fun BillDetail.toNewJobDetailLocal(newJobId: Int) = JobDetailEntity(
    id = id,
    jobId = newJobId,
    serviceId = serviceId,
    servicePrice = servicePrice,
    paidPrice = paidPrice
)

@JvmName("toJobDetailLocalEntity")
fun List<BillDetail>.toJobDetailLocalEntity() = map(BillDetail::toJobDetailLocal)

fun JobDetailEntity.toDomainModel() = BillDetail(
    id = id,
    jobId = jobId,
    serviceId = serviceId,
    servicePrice = servicePrice,
    paidPrice = paidPrice
)

@JvmName("localToExternal")
fun List<JobDetailEntity>.toDomainModel() = map(JobDetailEntity::toDomainModel)
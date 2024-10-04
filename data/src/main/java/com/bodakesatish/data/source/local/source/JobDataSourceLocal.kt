package com.bodakesatish.data.source.local.source

import android.util.Log
import com.bodakesatish.data.mapper.local.toDomainModel
import com.bodakesatish.data.source.local.dao.CustomerDao
import com.bodakesatish.data.source.local.dao.JobDao
import com.bodakesatish.data.source.local.dao.JobDetailDao
import com.bodakesatish.data.source.local.entity.JobDetailEntity
import com.bodakesatish.data.source.local.entity.JobEntity
import com.bodakesatish.domain.model.response.Bill
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobDataSourceLocal
@Inject
constructor(
    private val templateDao: JobDao,
    private val jobDetailDao: JobDetailDao,
    private val customerDao: CustomerDao
) {

    private val tag = this.javaClass.simpleName

    suspend fun insertJob(service: JobEntity): Int {
        val serviceId = templateDao.insertJob(service).toInt()
        return serviceId
    }

    suspend fun deleteAllTemplateList() {
        val data = templateDao.deleteAllJobList()
    }

    suspend fun getJobList(): List<Bill> {
        Log.i(tag, "In $tag getServicesList")

        val data = templateDao.getJobList().map { jobEntity ->
            val job = jobEntity.toDomainModel()
            val customer = customerDao.getCustomer(job.customerId)
            job.customerName = customer.customerName
            job.serviceList = jobDetailDao.getJobDetailList(job.id).joinToString(", ")
            job
        }

        Log.i(tag, "In $tag $data")

        return data
    }

    suspend fun insertJobDetail(jobDetailList: List<JobDetailEntity>) {
        val data = jobDetailDao.insertAllJobDetailList(jobDetailList)
    }

}
package com.bodakesatish.data.source.local.source

import android.util.Log
import com.bodakesatish.data.mapper.local.TemplateLocalMapper
import com.bodakesatish.data.mapper.local.toDomainModel
import com.bodakesatish.data.mapper.local.toLocalBookingEntity
import com.bodakesatish.data.source.DataSource
import com.bodakesatish.data.source.base.RemoteResponseCode
import com.bodakesatish.data.source.base.BaseOutput
import com.bodakesatish.data.source.local.dao.BookingDao
import com.bodakesatish.domain.model.response.Booking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookingsDataSourceLocal
@Inject
constructor(
    private val templateDao: BookingDao
) : DataSource.BookingSource {

    private val tag = this.javaClass.simpleName

    override suspend fun getBookingList(
        pageSize: Int,
        offset: Int,
        searchQuery: String
    ): BaseOutput<List<Booking>> {
        val data = templateDao.getTemplateList(pageSize, offset)
        return BaseOutput.Success(RemoteResponseCode.SUCCESS, data.toDomainModel())
    }

    suspend fun insertAllBookingList(list: List<Booking>) {
        val data = templateDao.insertAllBookingList(list.toLocalBookingEntity())
    }

    suspend fun deleteAllBookingList() {
        val data = templateDao.deleteAllBookingList()
    }

    suspend fun getBookingListFlow(searchQuery: String): BaseOutput<Flow<List<Booking>>> {
        Log.i(tag, "In $tag getBookingListFlow")

        val data = templateDao.searchBookingListFlow().map { templateEntityList ->
           templateEntityList.toDomainModel()
        }
        Log.i(tag, "In $tag $data")

        return if (data.firstOrNull()?.isEmpty() == true) {
            BaseOutput.Success(RemoteResponseCode.EMPTY, data)
        } else {
            BaseOutput.Success(RemoteResponseCode.SUCCESS, data)
        }

    }

}
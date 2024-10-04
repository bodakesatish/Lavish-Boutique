package com.bodakesatish.templates.ui.bill

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.model.response.Bill
import com.bodakesatish.domain.model.response.BillDetail
import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.domain.usecases.CreateOrEditBillUseCase
import com.bodakesatish.domain.usecases.GetCustomerListUseCase
import com.bodakesatish.domain.usecases.GetServiceListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CreateOrEditBillViewModel @Inject constructor(
    private val createOrEditServiceUseCase: CreateOrEditBillUseCase,
    private val getServiceListUseCase: GetServiceListUseCase,
    private val getCustomerListUseCase: GetCustomerListUseCase
) : ViewModel() {

    init {
        getServiceList()
        getCustomerList()
    }

    private val tag = this.javaClass.simpleName

    private val _addJobResponse = MutableLiveData<Boolean>()
    val addJobResponse: LiveData<Boolean> = _addJobResponse

    private val _serviceListResponse = MutableLiveData<List<Service>>()
    val serviceListResponse: LiveData<List<Service>> = _serviceListResponse

    private var _customerList = ArrayList<Customer>()

    private var selectedCustomer = Customer()
    private var totalBill = 0

    fun createOrEditJob(serviceList: List<Service>) {
        Log.d(tag, "In $tag createOrEditJob")
        val jobList = ArrayList<BillDetail>()
        serviceList.map {
            jobList.add(BillDetail(
                jobId = 0,
                serviceId = it.id,
                servicePrice = it.servicePrice,
                paidPrice = it.servicePrice
            ))
        }
        val job = Bill(
            customerId = selectedCustomer.id,
            jobDate = Date(),
            jobTotalPrice = totalBill,
            jobDetailList = jobList
        )
        viewModelScope.launch(Dispatchers.IO) {
            val request = CreateOrEditBillUseCase.Request()
            request.setRequestModel(job)
            val response = createOrEditServiceUseCase.executeUseCase(request)
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag createOrEditJob SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                        _addJobResponse.postValue(true)
                    }

                    else -> {
                        Log.d(tag, "In $tag createOrEditJob ERROR")
                        _addJobResponse.postValue(false)

                    }
                }
            }
        }
    }

    fun getServiceList() {
        Log.d(tag, "In $tag getServiceList")
        viewModelScope.launch(Dispatchers.IO) {
            val response = getServiceListUseCase.executeUseCase(GetServiceListUseCase.Request())
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag getServiceList SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                        _serviceListResponse.postValue(response.getData())
                    }

                    else -> {
                        Log.d(tag, "In $tag getServiceList ERROR")
                    }
                }
            }
        }
    }

    fun getCustomerList() {
        Log.d(tag, "In $tag getCustomerList")
        viewModelScope.launch(Dispatchers.IO) {
            val response = getCustomerListUseCase.executeUseCase(GetCustomerListUseCase.Request())
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag getCustomerList SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                        _customerList = response.getData() as ArrayList<Customer>
                    }

                    else -> {
                        Log.d(tag, "In $tag getCustomerList ERROR")
                    }
                }
            }
        }
    }

    fun getCustomerListModel(): List<Customer> {
        return _customerList
    }

    fun setCustomer(customer: Customer) {
        selectedCustomer = customer
    }

    fun setTotalBill(totalBill: Int) {
        this.totalBill = totalBill
    }

}

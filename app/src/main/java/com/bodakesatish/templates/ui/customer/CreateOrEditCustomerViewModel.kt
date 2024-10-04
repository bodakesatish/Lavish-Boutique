package com.bodakesatish.templates.ui.customer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.usecases.CreateOrEditCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOrEditCustomerViewModel @Inject constructor(
    private val createOrEditServiceUseCase: CreateOrEditCustomerUseCase
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    private val _addCustomerResponse = MutableLiveData<Boolean>()
    val addCustomerResponse : LiveData<Boolean> = _addCustomerResponse

    fun createOrEditCustomer(service: Customer) {
        Log.d(tag, "In $tag createOrEditCustomer")
        viewModelScope.launch(Dispatchers.IO) {
            val request = CreateOrEditCustomerUseCase.Request()
            request.setRequestModel(service)
            val response = createOrEditServiceUseCase.executeUseCase(request)
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag createOrEditCustomer SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                        _addCustomerResponse.postValue(true)
                    }
                    else -> {
                        Log.d(tag, "In $tag createOrEditCustomer ERROR")
                        _addCustomerResponse.postValue(false)

                    }
                }
            }
        }
    }

}

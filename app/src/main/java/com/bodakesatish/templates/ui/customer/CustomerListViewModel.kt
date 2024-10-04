package com.bodakesatish.templates.ui.customer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.response.Customer
import com.bodakesatish.domain.usecases.GetCustomerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerListViewModel @Inject constructor(
    private val getCustomerListUseCase: GetCustomerListUseCase
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    private val _customerListResponse = MutableLiveData<List<Customer>>()
    val customerListResponse : LiveData<List<Customer>> = _customerListResponse

    fun getCustomerList() {
        Log.d(tag, "In $tag getCustomerList")
        viewModelScope.launch(Dispatchers.IO) {
            val response = getCustomerListUseCase.executeUseCase(GetCustomerListUseCase.Request())
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag getCustomerList SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                        _customerListResponse.postValue(response.getData())
                    }
                    else -> {
                        Log.d(tag, "In $tag getCustomerList ERROR")
                    }
                }
            }
        }
    }

}

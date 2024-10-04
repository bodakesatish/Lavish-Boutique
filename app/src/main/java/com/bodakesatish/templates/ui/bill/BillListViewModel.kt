package com.bodakesatish.templates.ui.bill

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.response.Bill
import com.bodakesatish.domain.usecases.GetBillListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillListViewModel @Inject constructor(
    private val getJobListUseCase: GetBillListUseCase
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    private val _customerListResponse = MutableLiveData<List<Bill>>()
    val customerListResponse : LiveData<List<Bill>> = _customerListResponse

    fun getJobList() {
        Log.d(tag, "In $tag getJobList")
        viewModelScope.launch(Dispatchers.IO) {
            val response = getJobListUseCase.executeUseCase(GetBillListUseCase.Request())
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag getJobList SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                        _customerListResponse.postValue(response.getData())
                    }
                    else -> {
                        Log.d(tag, "In $tag getJobList ERROR")
                    }
                }
            }
        }
    }

}

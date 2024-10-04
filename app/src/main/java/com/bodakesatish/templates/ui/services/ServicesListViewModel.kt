package com.bodakesatish.templates.ui.services

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.model.response.Service
import com.bodakesatish.domain.model.response.Template
import com.bodakesatish.domain.repository.TemplateRepository
import com.bodakesatish.domain.usecases.GetServiceListUseCase
import com.bodakesatish.domain.usecases.TemplateFlowUseCase
import com.bodakesatish.domain.usecases.TemplateUseCase
import com.bodakesatish.templates.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicesListViewModel @Inject constructor(
    private val getServiceListUseCase: GetServiceListUseCase
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    private val _serviceListResponse = MutableLiveData<List<Service>>()
    val serviceListResponse : LiveData<List<Service>> = _serviceListResponse

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

}

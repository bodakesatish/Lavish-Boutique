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
import com.bodakesatish.domain.usecases.CreateOrEditServiceUseCase
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
class CreateOrEditServicesViewModel @Inject constructor(
    private val createOrEditServiceUseCase: CreateOrEditServiceUseCase
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    private val _addServiceResponse = MutableLiveData<Boolean>()
    val addServiceResponse : LiveData<Boolean> = _addServiceResponse

    fun createOrEditService(service: Service) {
        Log.d(tag, "In $tag createOrEditService")
        viewModelScope.launch(Dispatchers.IO) {
            val request = CreateOrEditServiceUseCase.Request()
            request.setRequestModel(service)
            val response = createOrEditServiceUseCase.executeUseCase(request)
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag createOrEditService SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                        _addServiceResponse.postValue(true)
                    }
                    else -> {
                        Log.d(tag, "In $tag createOrEditService ERROR")
                        _addServiceResponse.postValue(false)

                    }
                }
            }
        }
    }

}

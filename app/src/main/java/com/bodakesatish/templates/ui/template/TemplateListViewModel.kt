package com.bodakesatish.templates.ui.template

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.model.response.Template
import com.bodakesatish.domain.repository.TemplateRepository
import com.bodakesatish.domain.usecases.TemplateFlowUseCase
import com.bodakesatish.domain.usecases.TemplateUseCase
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
class TemplateListViewModel @Inject constructor(
    val repository: TemplateRepository,
    private val templateUseCase: TemplateUseCase,
    private val templateFlowUseCase: TemplateFlowUseCase

) : ViewModel() {

    // To show loading bar
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    // To show error message
    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?>
        get() = _errorMessage

    private val searchString: MutableStateFlow<String?> = MutableStateFlow(null)

    private val _pokemonDetail: MutableStateFlow<List<Template>?> = MutableStateFlow(null)
    val pokemonDetail: StateFlow<List<Template>?>
        get() = _pokemonDetail

    private val tag = this.javaClass.simpleName


    private fun getTemplateListFlowOld(searchString: String) {
//        Log.d(tag, "In $tag getTemplateListFlow")
//        viewModelScope.launch {
//
//            repository.getTemplateListFlow(
//                searchString, onStart = {
//                    Log.d(tag, "In $tag onStart")
//                    _isLoading.value = true
//                    _errorMessage.value = null
//                },
//                onComplete = {
//                    Log.d(tag, "In $tag onComplete")
//                    _isLoading.value = false
//                },
//                onError = {
//                    Log.d(tag, "In $tag onError")
//                    _errorMessage.value = it
//                }
//            ).collect {
//                Log.d(tag, "In $tag collect")
//                _pokemonDetail.emit(it)
//            }
//        }
    }

    fun getTemplateList(searchText: String) {
        Log.d(tag, "In $tag getSchemeList")
        viewModelScope.launch(Dispatchers.IO) {
            val request = TemplateUseCase.Request()
            request.setRequestModel(
                TemplateRequest(
                    currentPage = 1,
                    pageSize = 1,
                    searchText = searchText
                )
            )

            val response = templateUseCase.executeUseCase(request)
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag getTemplateList SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                    }

                    else -> {
                        Log.d(tag, "In $tag getTemplateList ERROR")
                    }
                }
            }
        }
    }

    fun getTemplateListFlow(searchString: String) {
        Log.d(tag, "In $tag getTemplateListFlow")
        Log.d(tag, "In $tag getSchemeList")
        viewModelScope.launch(Dispatchers.IO) {
            val request = TemplateFlowUseCase.Request()
            request.setRequestModel(
                TemplateRequest(
                    currentPage = 1,
                    pageSize = 1,
                    searchText = searchString
                )
            )

            val response = templateFlowUseCase.executeUseCase(request)
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag getTemplateListFlow2 SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")

                        response.getData()?.collect {
                            _pokemonDetail.emit(it)
                        }

                    }

                    else -> {
                        Log.d(tag, "In $tag getTemplateList ERROR")
                    }
                }
            }
        }
    }
}
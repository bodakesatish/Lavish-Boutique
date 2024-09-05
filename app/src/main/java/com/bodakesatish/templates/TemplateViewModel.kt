package com.bodakesatish.templates

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.domain.model.ResponseCode
import com.bodakesatish.domain.model.request.TemplateRequest
import com.bodakesatish.domain.usecases.TemplateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor(
    private val templateUseCase: TemplateUseCase
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    private var currentPage = 0
    private val pageSize = 20

    init {
        Log.d(tag, "In $tag init")
    }

    fun getTemplateList(searchText: String) {
        Log.d(tag, "In $tag getSchemeList")
        viewModelScope.launch(Dispatchers.IO) {
            val request = TemplateUseCase.Request()
            request.setRequestModel(
                TemplateRequest(
                    currentPage = currentPage,
                    pageSize = pageSize,
                    searchText = searchText
                )
            )

            val response = templateUseCase.executeUseCase(request)
            viewModelScope.launch(Dispatchers.Main) {
                when (response.getResponseCode()) {
                    is ResponseCode.Success -> {
                        Log.d(tag, "In $tag getTemplateList SUCCESS")
                        Log.d(tag, "In $tag Response -> ${response.getData()}")
                        currentPage = 0
                    }

                    else -> {
                        Log.d(tag, "In $tag getTemplateList ERROR")
                    }
                }
            }
        }
    }

}
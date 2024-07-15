package com.course.profit.main.aboutedit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.profit.data.ProfitRepository
import com.course.profit.data.model.UpdateDescriptionRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutEditViewModel @Inject constructor(
    private val repository: ProfitRepository,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (description.isBlank()) {
                description = repository.getUserInfo().getOrNull()!!.description
            }
        }
    }

    var description by mutableStateOf("")


    fun updateDescription() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDescription(UpdateDescriptionRequest(description = description))
        }
    }
}

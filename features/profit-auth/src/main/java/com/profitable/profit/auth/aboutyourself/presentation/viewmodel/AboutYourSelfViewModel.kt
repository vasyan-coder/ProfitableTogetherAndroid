package com.profitable.profit.auth.aboutyourself.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.profit.data.ProfitRepository
import com.course.profit.data.model.UpdateDescriptionRequest
import com.profitable.profit.auth.aboutyourself.domain.SaveAboutYourSelfUseCase
import com.profitable.profit.auth.aboutyourself.presentation.model.AboutStateUI
import com.profitable.profit.auth.aboutyourself.presentation.viewmodel.model.AboutMainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutYourSelfViewModel @Inject constructor(
    private val saveAboutYourSelfUseCase: SaveAboutYourSelfUseCase,
    private val repository: ProfitRepository,
) : ViewModel() {

    var aboutStateUI by mutableStateOf(AboutStateUI())
        private set

    fun updateDescription() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDescription(UpdateDescriptionRequest(description = aboutStateUI.text))
        }
    }

    fun onEvent(event: AboutMainEvent) {
        when (event) {
            is AboutMainEvent.Save -> {
                TODO()
            }

            is AboutMainEvent.TextChanged -> {
                if (event.text.length < 500) {
                    aboutStateUI = aboutStateUI.copy(text = event.text, isError = false)
                }
            }

            is AboutMainEvent.IsError -> {
                viewModelScope.launch {
                    aboutStateUI = aboutStateUI.copy(isError = true)
                    delay(3000L)
                    aboutStateUI = aboutStateUI.copy(isError = false)
                }
            }
        }
    }

}

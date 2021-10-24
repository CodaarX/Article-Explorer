package com.decagon.edconnect.presentation.viewmodels

import androidx.lifecycle.*
import com.decagon.edconnect.domain.model.DomainProject
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.domain.repository.UserRepository
import com.decagon.edconnect.utils.Resource
import com.decagon.edconnect.utils.UiStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val _userProfileState = MutableLiveData<UiStateManager<DomainUser>>(UiStateManager())
    val userProfileState: LiveData<UiStateManager<DomainUser>> = _userProfileState

    private fun <T> uiResponse(result: Resource<T>, state: MutableLiveData<UiStateManager<T>>) {
        when (result) {
            is Resource.Success -> {
                state.value = UiStateManager(data = result.data)
            }
            is Resource.Error -> {
                state.value =
                    UiStateManager(error = result.message ?: "An unexpected error occurred")
            }
            is Resource.Loading -> {
                state.value = UiStateManager(isLoading = true)
            }
        }
    }

    fun getProfile(id: String) {
        viewModelScope.launch {
            userRepository.getProfile(id).collect {
                uiResponse(it, _userProfileState)
            }
        }
    }

    fun updateProject(id: String, user: DomainUser) {
        viewModelScope.launch {
            userRepository.updateProfile(id, user).collect {
                uiResponse(it, _userProfileState)
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            userRepository.logout()
        }
    }
}


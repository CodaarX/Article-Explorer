package com.decagon.edconnect.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.domain.repository.AuthRepository
import com.decagon.edconnect.utils.GenericResponseClass
import com.decagon.edconnect.utils.Resource
import com.decagon.edconnect.utils.UiStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _userProfileState = MutableLiveData<UiStateManager<DomainUser>>(UiStateManager())
    val userProfileState: LiveData<UiStateManager<DomainUser>> = _userProfileState

    private val _authenticationState = MutableLiveData<UiStateManager<GenericResponseClass<String>>>(UiStateManager())
    val authenticationState: LiveData<UiStateManager<GenericResponseClass<String>>> = _authenticationState

    private fun <T> uiResponse(result: Resource<T>, state: MutableLiveData<UiStateManager<T>>) {
        when (result) {
            is Resource.Success -> {
                state.value = UiStateManager(data = result.data)
            }
            is Resource.Error -> {
                state.value = UiStateManager(error = result.message ?: "An unexpected error occurred")
            }
            is Resource.Loading -> {
                state.value = UiStateManager(isLoading = true)
            }
        }
    }


    fun registerUser(user: DomainUser) {
        viewModelScope.launch {
            authRepository.registerUser(user).also {
                uiResponse(it, _authenticationState)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect {
                uiResponse(it, _userProfileState)
            }
        }
    }

    fun loginUserWithGoogle(){
        viewModelScope.launch {
            authRepository.loginUserWithGoogle().collect{
                uiResponse(it, _userProfileState)
            }
        }
    }

    fun loginUserWithFacebook(){
        viewModelScope.launch {
            authRepository.loginUserWithFacebook().collect {
                uiResponse(it, _userProfileState)
            }
        }
    }

    fun requestPasswordChange(email: String){
        viewModelScope.launch {
            authRepository.requestPasswordChange(email).also {
                uiResponse(it, _authenticationState)
            }
        }
    }

    fun changePassword(oldPassword: String, newPassword: String, confirmNewPassword: String){
        viewModelScope.launch {
            authRepository.changePassword(oldPassword, newPassword, confirmNewPassword).also {
                uiResponse(it, _authenticationState)
            }
        }
    }

    fun resetPassword(token: String, password: String, confirmPassword: String){
        viewModelScope.launch {
            authRepository.resetPassword(token, password, confirmPassword).also {
                uiResponse(it, _authenticationState)
            }
        }
    }


}
package com.decagon.edconnect.presentation.viewmodels

import android.view.SearchEvent
import androidx.lifecycle.*
import com.decagon.edconnect.domain.model.DomainProject
import com.decagon.edconnect.domain.repository.ProjectRepository
import com.decagon.edconnect.utils.Resource
import com.decagon.edconnect.utils.UiStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _projectListState = MutableLiveData<UiStateManager<List<DomainProject>>>(UiStateManager())
    val projectListState: LiveData<UiStateManager<List<DomainProject>>> = _projectListState

    private val _singleProjectState = MutableLiveData<UiStateManager<DomainProject>>(UiStateManager())
    val singleProjectState: LiveData<UiStateManager<DomainProject>> = _singleProjectState

    private val _favouriteProjectListState = MutableLiveData<UiStateManager<List<DomainProject>>>(UiStateManager())
    val favouriteProjectListState: LiveData<UiStateManager<List<DomainProject>>> = _favouriteProjectListState

    private fun <T> uiResponse(result: Resource<T>, state: MutableLiveData<UiStateManager<T>>){
        when (result) {
            is Resource.Success -> {
                state.value = UiStateManager(data = result.data)
            }
            is Resource.Error -> {
                state.value = UiStateManager(error = result.message?: "An unexpected error occurred")
            }
            is Resource.Loading -> {
                state.value = UiStateManager(isLoading = true)
            }
        }
    }

    fun getAllProjects() {
        viewModelScope.launch {
            projectRepository.getAllProjects(false).collect { localData ->
                if (localData.data?.isNullOrEmpty() == true) {
                    projectRepository.getAllProjects(true).collect { remoteData ->
                        uiResponse(remoteData, _favouriteProjectListState)
                    }
                } else {
                    uiResponse(localData, _projectListState)
                }
            }
        }
    }

    fun getSingleProject(id: String){
        viewModelScope.launch {
            projectRepository.getSingleProject(id, false).collect { localData ->
                uiResponse(localData, _singleProjectState)
            }
        }
    }

    fun updateProject(id: String, project: DomainProject){
        viewModelScope.launch {
            projectRepository.updateProject(id, project).collect {
                uiResponse(it, _singleProjectState)
            }
        }
    }

    fun createProject(project: DomainProject){
        viewModelScope.launch {
            projectRepository.createProject(project).collect {
                uiResponse(it, _projectListState)
            }
        }
    }

    fun deleteProject(id: String){
        viewModelScope.launch {
            projectRepository.deleteProject(id).collect {
                uiResponse(it, _projectListState)
            }
        }
    }

    fun getFavouriteProjects() {
        viewModelScope.launch {
            projectRepository.getFavouriteProjects(false).collect { localData ->
                if (localData.data?.isNullOrEmpty() == true) {
                    projectRepository.getFavouriteProjects(true).collect { remoteData ->
                        uiResponse(remoteData, _favouriteProjectListState)
                    }
                } else {
                    uiResponse(localData, _favouriteProjectListState)
                }
            }
        }

        fun deleteFavouriteProject(id: String) {
            viewModelScope.launch {
                projectRepository.deleteFavouriteProject(id).collect {
                    uiResponse(it, _favouriteProjectListState)
                }
            }
        }

        fun addFavouriteProject(id: String) {
            viewModelScope.launch {
                projectRepository.addFavouriteProject(id).collect {
                    uiResponse(it, _favouriteProjectListState)
                }
            }
        }

        fun searchProjects(text: String) {
            var searchJob: Job? = null

            if (searchJob?.isActive == true) searchJob.cancel( CancellationException("New search parameters incoming!"))

            else searchJob = viewModelScope.launch {
                projectRepository.searchProject(text, false).collect {
                uiResponse(it, _favouriteProjectListState)
                }
            }
        }

        fun projectsCreatedByMe() {
            viewModelScope.launch {
                projectRepository.projectsCreatedByMe(false).collect { localData ->
                    if (localData.data.isNullOrEmpty()) {
                        projectRepository.projectsCreatedByMe(true).collect { remoteData ->
                            uiResponse(remoteData, _favouriteProjectListState)
                        }
                    } else {
                        uiResponse(localData, _favouriteProjectListState)
                    }
                }
            }
        }
    }

}
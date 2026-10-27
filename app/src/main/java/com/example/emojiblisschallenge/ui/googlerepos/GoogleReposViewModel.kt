package com.example.emojiblisschallenge.ui.googlerepos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.emojiblisschallenge.data.GoogleRepoRepository
import com.example.emojiblisschallenge.data.RetrofitClient
import com.example.emojiblisschallenge.model.GoogleRepo
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GoogleReposViewModel(
    private val repository: GoogleRepoRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(GoogleReposUiState())
    val uiState = _uiState.asStateFlow()

    private var nextPage = 1
    private val pageSize = 20

    init {
        loadNextPage()
    }

    fun loadNextPage(){
        if (_uiState.value.isLoading || _uiState.value.endReached) return

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                val newRepos = repository.getRepos(
                    page = nextPage,
                    perPage = pageSize
                )

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    repos = _uiState.value.repos + newRepos,
                    endReached = newRepos.size < pageSize
                )
                nextPage ++
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Couldn't load repositories"
                )
            }
        }
    }
}

val googleReposViewModelFactory = viewModelFactory {
    initializer {
        GoogleReposViewModel(
            repository = GoogleRepoRepository(
                api = RetrofitClient.api
            )
        )
    }
}
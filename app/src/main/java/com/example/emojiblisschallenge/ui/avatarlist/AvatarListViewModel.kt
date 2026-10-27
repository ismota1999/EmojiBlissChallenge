package com.example.emojiblisschallenge.ui.avatarlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.emojiblisschallenge.data.AvatarRepository
import com.example.emojiblisschallenge.data.RetrofitClient
import com.example.emojiblisschallenge.data.local.AppDatabase
import com.example.emojiblisschallenge.data.local.AvatarDao
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class AvatarListViewModel (
    private val avatarRepository: AvatarRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AvatarListUiState())

    val uiState = _uiState.asStateFlow()

    init {
        loadAvatars()
    }

    fun loadAvatars() {
        if (_uiState.value.isLoading) return

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                val loadedAvatars = avatarRepository.getSavedAvatars()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    avatars = loadedAvatars,
                    errorMessage = null
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Couldn't load avatars."
                )
            }
        }
    }

    fun deleteAvatar(avatarId: Long) {
        viewModelScope.launch {
            try {
                avatarRepository.deleteAvatar(avatarId = avatarId)
                _uiState.value = _uiState.value.copy(
                    avatars = _uiState.value.avatars.filterNot { avatar ->
                        avatar.id == avatarId
                    },
                    errorMessage = null
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Couldn't delete avatar."
                )
            }
        }
    }
}

val avatarListViewModelFactory = viewModelFactory {
    initializer {
        val application = checkNotNull(this[APPLICATION_KEY])
        val database = AppDatabase.getInstance(application)

        AvatarListViewModel(
            avatarRepository = AvatarRepository(
                api = RetrofitClient.api,
                avatarDao = database.avatarDao()
            )
        )
    }
}
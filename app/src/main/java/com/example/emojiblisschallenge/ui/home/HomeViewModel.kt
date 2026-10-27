package com.example.emojiblisschallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.emojiblisschallenge.data.AvatarRepository
import com.example.emojiblisschallenge.data.EmojiRepository
import com.example.emojiblisschallenge.data.RetrofitClient
import com.example.emojiblisschallenge.data.local.AppDatabase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: EmojiRepository,
    private val avatarRepository: AvatarRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())

    val uiState = _uiState.asStateFlow()

    fun onUsernameChanged(username: String) {
        _uiState.value = _uiState.value.copy(
            usernameInput = username
        )
    }

    fun searchAvatar() {
        if (_uiState.value.isSearchingAvatar) return

        val username = _uiState.value.usernameInput.trim()

        if (username.isBlank()){
            _uiState.value = _uiState.value.copy(
                avatarErrorMessage = "Enter a github username."
            )
            return
        }

        _uiState.value = _uiState.value.copy(
            isSearchingAvatar = true,
            avatarErrorMessage = null,
            searchedAvatar = null
        )

        viewModelScope.launch {
            try {
                val avatar = avatarRepository.getAvatar(username = username)
                _uiState.value = _uiState.value.copy(
                    isSearchingAvatar = false,
                    searchedAvatar = avatar
                )
            } catch (exception: CancellationException){
                throw exception
            } catch (exception: Exception){
                _uiState.value = _uiState.value.copy(
                    isSearchingAvatar = false,
                    avatarErrorMessage = "Couldn't locate user or load avatar"
                )
            }
        }

    }


    fun loadEmojis() {
        if (_uiState.value.isLoading) return

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                val loadedEmojis = repository.getEmojis()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    emojis = loadedEmojis,
                    selectedEmoji = loadedEmojis.randomOrNull()
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Couldn't load emojis, please try again in a few moments."
                )
            }
        }

    }
}

val homeViewModelFactory = viewModelFactory {
    initializer {
        val application = checkNotNull(this[APPLICATION_KEY])
        val database = AppDatabase.getInstance(application)

        HomeViewModel(
            repository = EmojiRepository(
                api = RetrofitClient.api,
                emojiDao = database.emojiDao()
            ),
            avatarRepository = AvatarRepository(
                api = RetrofitClient.api,
                avatarDao = database.avatarDao()
            )
        )
    }
}
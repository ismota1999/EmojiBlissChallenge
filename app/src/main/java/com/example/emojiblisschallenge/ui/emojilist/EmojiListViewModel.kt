package com.example.emojiblisschallenge.ui.emojilist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.emojiblisschallenge.data.EmojiRepository
import com.example.emojiblisschallenge.data.RetrofitClient
import com.example.emojiblisschallenge.data.local.AppDatabase
import com.example.emojiblisschallenge.data.local.EmojiDao
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmojiListViewModel (
    private val emojiRepository: EmojiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EmojiListUiState())

    val uiState = _uiState.asStateFlow()

    init {
        loadEmojis()
    }

    fun loadEmojis() {
        if (_uiState.value.isLoading) return

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                val loadedEmojis = emojiRepository.getEmojis()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    emojis = loadedEmojis,
                    errorMessage = null,
                    loadVersion = _uiState.value.loadVersion + 1
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Couldn't load emojis."
                )
            }
        }
    }

    fun removeEmoji (emojiName: String) {
        _uiState.value = _uiState.value.copy(
            emojis = _uiState.value.emojis.filterNot { emoji->
                emoji.emojiName == emojiName
            }
        )
    }
}

val emojiListViewModelFactory = viewModelFactory {
    initializer {
        val application = checkNotNull(this[APPLICATION_KEY])
        val database = AppDatabase.getInstance(application)

        EmojiListViewModel(
            emojiRepository = EmojiRepository(
                api = RetrofitClient.api,
                emojiDao = database.emojiDao()
            )
        )
    }
}
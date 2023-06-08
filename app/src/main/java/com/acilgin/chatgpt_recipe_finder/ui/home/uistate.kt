package com.acilgin.chatgpt_recipe_finder.ui.home

import com.acilgin.chatgpt_recipe_finder.data.model.Content

// generic ui state class to handle the response
sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class SuccessChat<Content>(val data: Content) : UiState<com.acilgin.chatgpt_recipe_finder.data.model.Content>()
    data class Error(val message: String) : UiState<Nothing>()
    data class Loading (val isLoading: Boolean): UiState<Nothing>()
}

sealed class ContentState<out T> {
    object Initial : ContentState<Nothing>()
    data class Success<out T>(val data: T) : ContentState<T>()
    data class Error(val message: String) : ContentState<Nothing>()
    data class Loading (val isLoading: Boolean): ContentState<Nothing>()
}

sealed class ImageState<out T> {
    object Initial : ImageState<Nothing>()
    data class Success<out T>(val data: T) : ImageState<T>()
    data class Error(val message: String) : ImageState<Nothing>()
    data class Loading (val isLoading: Boolean): ImageState<Nothing>()
}
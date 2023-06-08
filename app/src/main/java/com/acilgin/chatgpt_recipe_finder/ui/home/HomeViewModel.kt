package com.acilgin.chatgpt_recipe_finder.ui.home

import androidx.lifecycle.*
import com.acilgin.chatgpt_recipe_finder.common.Resource
import com.acilgin.chatgpt_recipe_finder.data.model.ChatResponseModel
import com.acilgin.chatgpt_recipe_finder.data.model.CompletionResponse
import com.acilgin.chatgpt_recipe_finder.data.model.Content
import com.acilgin.chatgpt_recipe_finder.data.model.convertJsonToContent
import com.acilgin.chatgpt_recipe_finder.domain.usecase.RecipeFinderUseCase
import com.acilgin.chatgpt_recipe_finder.domain.usecase.GenerateImageUseCase
import com.acilgin.chatgpt_recipe_finder.domain.usecase.GenerateRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val generateRecipeUseCase: GenerateRecipeUseCase,
    private val recipeFinderUseCase: RecipeFinderUseCase,
    private val imageUseCase: GenerateImageUseCase
) : ViewModel() {


    val _recipeUiState = MutableStateFlow<UiState<String>>(UiState.Initial)
    val uiState: StateFlow<UiState<String>> = _recipeUiState

    val _contentState = MutableStateFlow<ContentState<Content?>>(ContentState.Initial)
    val contentState: StateFlow<ContentState<Content?>> = _contentState

    val _imageState = MutableStateFlow<ImageState<String>>(ImageState.Initial)
    val imageState: StateFlow<ImageState<String>> = _imageState


    fun setLoading(isLoading: Boolean) {
       _recipeUiState.value = UiState.Loading(isLoading)
    }
    // emit the usecase and handle the response
    fun generateRecipe(prompt: String) = viewModelScope.launch {
        generateRecipeUseCase(prompt).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    setLoading(false)
                    _recipeUiState.value = UiState.Success(getTextFromResponse(result.data))
                }
                is Resource.Error -> {
                    setLoading(false)
                    _recipeUiState.value = UiState.Error(result.toString())
                }
                is Resource.Loading -> {
                    setLoading(true)
                }

            }

        }.launchIn(viewModelScope)
    }

    fun generateRecipeFromChat(ingredients: String,type: String,language: String) =viewModelScope.launch {
        recipeFinderUseCase(ingredients = ingredients,type =type,language = language).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _contentState.value = ContentState.Success(getStringFromChatResponce(result.data))!!
                }
                is Resource.Error -> {
                    setLoading(false)
                    _recipeUiState.value = UiState.Error(result.toString())
                }
                is Resource.Loading -> {
                    setLoading(true)
                }

            }

        }.launchIn(viewModelScope)
    }

    fun generateRecipeImage(prompt: String) = viewModelScope.launch {
        imageUseCase(prompt).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _imageState.value = ImageState.Success(result.data.data[0].url.toString())
                }
                is Resource.Error -> {
                     _imageState.value = ImageState.Error(result.toString())
                    _imageState.value = ImageState.Error(result.toString())
                }
                is Resource.Loading -> {
                   _imageState.value = ImageState.Loading(true)
                }

            }

        }.launchIn(viewModelScope)
    }


    private fun getStringFromChatResponce(response : ChatResponseModel) : Content? {
           return response.choices[0].message?.content?.let { convertJsonToContent(it) }
    }

    // get text from CompletionResponse
    private fun getTextFromResponse(response: CompletionResponse): String {
        var text = ""
        response.choices.forEach {
            text += it.text
        }
        return text
    }

}
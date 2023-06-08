package com.acilgin.chatgpt_recipe_finder.domain.source

import com.acilgin.chatgpt_recipe_finder.data.model.*
import retrofit2.http.Body

interface RemoteDataSource {

    suspend fun generateRecipe(@Body body: CompletionRequestBody): CompletionResponse

    suspend fun generateChat(@Body body: ChatRequestBody): ChatResponseModel

    suspend fun generateImage(@Body body: ImageRequestBody): ImageResponseBody

}
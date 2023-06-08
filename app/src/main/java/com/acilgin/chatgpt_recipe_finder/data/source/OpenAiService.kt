package com.acilgin.chatgpt_recipe_finder.data.source

import com.acilgin.chatgpt_recipe_finder.data.model.*
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAiService {

    @POST("completions")
    suspend fun generateRecipe(@Body body: CompletionRequestBody): CompletionResponse

    @POST("chat/completions")
    suspend fun generateChat(@Body body: ChatRequestBody): ChatResponseModel

    @POST("images/generations")
    suspend fun generateImage(@Body body: ImageRequestBody): ImageResponseBody
}
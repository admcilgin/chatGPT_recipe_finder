package com.acilgin.chatgpt_recipe_finder.data.source

import com.acilgin.chatgpt_recipe_finder.data.model.*
import com.acilgin.chatgpt_recipe_finder.domain.source.RemoteDataSource

class RemoteDataSourceImpl(private val remoteService : OpenAiService) : RemoteDataSource {

    override suspend fun generateRecipe(body: CompletionRequestBody): CompletionResponse {
        return remoteService.generateRecipe(body)
    }

    override suspend fun generateChat(body: ChatRequestBody): ChatResponseModel {
        return remoteService.generateChat(body)
    }

    override suspend fun generateImage(body: ImageRequestBody): ImageResponseBody {
        return remoteService.generateImage(body)
    }


}

package com.acilgin.chatgpt_recipe_finder.domain.repository

import com.acilgin.chatgpt_recipe_finder.common.Resource
import com.acilgin.chatgpt_recipe_finder.data.model.ChatRequestBody
import com.acilgin.chatgpt_recipe_finder.data.model.ChatResponseModel
import com.acilgin.chatgpt_recipe_finder.data.model.CompletionRequestBody
import com.acilgin.chatgpt_recipe_finder.data.model.CompletionResponse
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun generateChat(completionRequestBody: ChatRequestBody): Flow<Resource<ChatResponseModel>>

}
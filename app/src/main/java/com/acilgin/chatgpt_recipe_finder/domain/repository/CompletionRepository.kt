package com.acilgin.chatgpt_recipe_finder.domain.repository

import com.acilgin.chatgpt_recipe_finder.common.Resource
import com.acilgin.chatgpt_recipe_finder.data.model.ChatRequestBody
import com.acilgin.chatgpt_recipe_finder.data.model.ChatResponseModel
import com.acilgin.chatgpt_recipe_finder.data.model.CompletionResponse
import com.acilgin.chatgpt_recipe_finder.data.model.CompletionRequestBody
import kotlinx.coroutines.flow.Flow

interface CompletionRepository {

     fun generateRecipe(completionRequestBody: CompletionRequestBody): Flow<Resource<CompletionResponse>>

}
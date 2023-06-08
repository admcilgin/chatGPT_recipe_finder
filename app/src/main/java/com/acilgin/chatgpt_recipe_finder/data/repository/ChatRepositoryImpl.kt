package com.acilgin.chatgpt_recipe_finder.data.repository

import android.util.Log
import com.acilgin.chatgpt_recipe_finder.common.Resource
import com.acilgin.chatgpt_recipe_finder.data.model.ChatRequestBody
import com.acilgin.chatgpt_recipe_finder.data.model.ChatResponseModel
import com.acilgin.chatgpt_recipe_finder.data.model.CompletionRequestBody
import com.acilgin.chatgpt_recipe_finder.domain.repository.ChatRepository
import com.acilgin.chatgpt_recipe_finder.domain.repository.CompletionRepository
import com.acilgin.chatgpt_recipe_finder.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ChatRepository {
    override fun generateChat(chatRequestBody: ChatRequestBody): Flow<Resource<ChatResponseModel>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = remoteDataSource.generateChat(chatRequestBody)
                Log.d("ADEM", "generateRecipe: $response")
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }

        }
    }




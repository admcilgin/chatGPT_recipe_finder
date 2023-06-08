package com.acilgin.chatgpt_recipe_finder.data.repository

import android.util.Log
import com.acilgin.chatgpt_recipe_finder.common.Resource
import com.acilgin.chatgpt_recipe_finder.data.model.ChatRequestBody
import com.acilgin.chatgpt_recipe_finder.data.model.ChatResponseModel
import com.acilgin.chatgpt_recipe_finder.data.model.ImageRequestBody
import com.acilgin.chatgpt_recipe_finder.data.model.ImageResponseBody
import com.acilgin.chatgpt_recipe_finder.domain.repository.ChatRepository
import com.acilgin.chatgpt_recipe_finder.domain.repository.ImageRepository
import com.acilgin.chatgpt_recipe_finder.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImageRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ImageRepository {
    override fun generateImage(imageRequestBody: ImageRequestBody): Flow<Resource<ImageResponseBody>> =
        flow {
            emit(Resource.Loading)
            try {
                val response = remoteDataSource.generateImage(imageRequestBody)
                Log.d("ADEM", "generateRecipe: $response")
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }

        }
}



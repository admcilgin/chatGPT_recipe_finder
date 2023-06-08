package com.acilgin.chatgpt_recipe_finder.data.repository

import android.util.Log
import com.acilgin.chatgpt_recipe_finder.common.Resource
import com.acilgin.chatgpt_recipe_finder.data.model.CompletionRequestBody
import com.acilgin.chatgpt_recipe_finder.domain.repository.CompletionRepository
import com.acilgin.chatgpt_recipe_finder.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.flow

class CompletionRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CompletionRepository {

    override fun generateRecipe(completionRequestBody: CompletionRequestBody)=
         flow {
            emit(Resource.Loading)
            try {
                val response = remoteDataSource.generateRecipe(completionRequestBody)
                Log.d("ADEM", "generateRecipe: $response")
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }

    }


}
package com.acilgin.chatgpt_recipe_finder.domain.repository

import com.acilgin.chatgpt_recipe_finder.common.Resource
import com.acilgin.chatgpt_recipe_finder.data.model.ImageRequestBody
import com.acilgin.chatgpt_recipe_finder.data.model.ImageResponseBody
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun generateImage(imageRequestBody: ImageRequestBody) : Flow<Resource<ImageResponseBody>>
}
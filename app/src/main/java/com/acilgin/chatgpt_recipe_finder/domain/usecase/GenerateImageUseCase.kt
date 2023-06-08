package com.acilgin.chatgpt_recipe_finder.domain.usecase

import com.acilgin.chatgpt_recipe_finder.data.model.ImageRequestBody
import com.acilgin.chatgpt_recipe_finder.domain.repository.ImageRepository
import javax.inject.Inject

class GenerateImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    operator fun invoke(prompt: String) = imageRepository.generateImage(ImageRequestBody(prompt))
}
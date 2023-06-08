package com.acilgin.chatgpt_recipe_finder.domain.usecase

import com.acilgin.chatgpt_recipe_finder.data.model.CompletionRequestBody
import com.acilgin.chatgpt_recipe_finder.domain.repository.CompletionRepository
import javax.inject.Inject


class GenerateRecipeUseCase @Inject constructor(
    private val completionRepository: CompletionRepository
) {
     operator fun invoke(prompt: String)  =
        completionRepository.generateRecipe(CompletionRequestBody(prompt = prompt))

}

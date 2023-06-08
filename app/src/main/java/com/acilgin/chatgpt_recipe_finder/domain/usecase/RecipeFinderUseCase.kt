package com.acilgin.chatgpt_recipe_finder.domain.usecase


import com.acilgin.chatgpt_recipe_finder.data.model.ChatRequestBody
import com.acilgin.chatgpt_recipe_finder.data.model.Messages
import com.acilgin.chatgpt_recipe_finder.domain.repository.ChatRepository
import javax.inject.Inject

class  RecipeFinderUseCase @Inject constructor(
    private val completionRepository: ChatRepository
) {
     operator fun invoke(ingredients: String,type: String,language: String)  =
        completionRepository.generateChat(ChatRequestBody(
            model = "gpt-3.5-turbo",
            messages = arrayListOf(
                Messages(
                    role = "user",
                    content = "You are a recipe finder bot. The user will provide a list of ingredients to you. " +
                            "Your task is to find recipes using the given ingredients. " +
                            "Ensure that the ingredients are real, edible, and commonly used in cooking." +
                            " Return the recipe, along with the list of ingredients and instructions." +
                            " Furthermore, generate a brief 30-character image prompt describing the recipe. " +
                            " And also put estimated calories and prepare time, type of dish, and the language of the recipe in the response. " +
                            "Return all the information in JSON format. " +
                            "The response should be in the language specified by the user. " +
                            "If you encounter an error, or cannot find a recipe with the provided ingredients, return a random recipe instead. " +
                            "Do not return any error messages or explanations. If the user provides unreal or inedible ingredients, or does not provide ingredients or the type of dish, return a random recipe. " +
                            "Your response should only contain the JSON content, and no additional text. Here is an example of the JSON format: \n" +
                            "\n" +
                            "    {\n" +
                            "        'name': 'Vanilla Cake',\n" +
                            "        'language': 'en',\n" +
                            "        'image_prompt': 'A cake with strawberries on top.',\n" +
                            "        'type': 'Dessert, Cake',\n" +
                            "        'calories': '490',\n" +
                            "        'prepare_time': '1 hour',\n" +
                            "        'ingredients': [\n" +
                            "            '2 cups of all-purpose flour',\n" +
                            "            '2 large eggs'\n" +
                            "        ],\n" +
                            "        'steps': [\n" +
                            "            'Preheat the oven to 350°F (175°C) and grease an 8-inch cake pan with butter or cooking spray.',\n" +
                            "            'In a medium bowl, whisk together the flour, baking powder, and salt. Set aside.'\n" +
                            "        ]\n" +
                            "    }\n" +
                            "\n" +
                            "Here are the ingredients: \" + $ingredients + \" and the type of dish: \" + $type + \". Please provide the recipe in this language: \" + $language",
                ),

            ),
        ))

}
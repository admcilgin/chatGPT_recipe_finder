package com.acilgin.chatgpt_recipe_finder.data.model

import com.google.gson.annotations.SerializedName

/**
 *   "model": "text-davinci-003",
"prompt": "Say this is a test",
"max_tokens": 7,
"temperature": 0,
"top_p": 1,
"n": 1,
"stream": false,
"logprobs": null,
"stop": "\n"
 */
data class CompletionRequestBody (
    @SerializedName("model"       ) var model       : String = "gpt-3.5-turbo",
    @SerializedName("prompt"      ) var prompt      : String = "This is a recipe finder app. Please give me a recipe name and I will find it for you.",
    @SerializedName("max_tokens"  ) var maxTokens   : Int   = 1200,
    @SerializedName("temperature" ) var temperature : Double  = 0.3,

)
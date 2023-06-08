package com.acilgin.chatgpt_recipe_finder.data.model

import com.google.gson.annotations.SerializedName

data class CompletionResponse (

    @SerializedName("id"      ) var id      : String?            = null,
    //@SerializedName("object"  ) var object  : String?            = null,
    @SerializedName("created" ) var created : Int?               = null,
    @SerializedName("model"   ) var model   : String?            = null,
    @SerializedName("choices" ) var choices : ArrayList<Choices> = arrayListOf(),
    //@SerializedName("usage"   ) var usage   : Usage?             = Usage()

)
data class Choices (

    @SerializedName("text"          ) var text         : String? = null,
    @SerializedName("index"         ) var index        : Int?    = null,
    @SerializedName("logprobs"      ) var logprobs     : String? = null,
    @SerializedName("finish_reason" ) var finishReason : String? = null

)
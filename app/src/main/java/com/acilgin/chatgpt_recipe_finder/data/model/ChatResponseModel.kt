package com.acilgin.chatgpt_recipe_finder.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


data class ChatResponseModel (

    @SerializedName("id"      ) var id      : String?            = null,
    @SerializedName("object"  ) var objec : String?            = null,
    @SerializedName("created" ) var created : Int?               = null,
    @SerializedName("choices" ) var choices : ArrayList<Choice> = arrayListOf(),
    @SerializedName("usage"   ) var usage   : Usage?             = Usage()

)

data class Message (

    @SerializedName("role"    ) var role    : String? = null,
    @SerializedName("content" ) var content : String? = null

)


data class Choice (

    @SerializedName("index"         ) var index        : Int?     = null,
    @SerializedName("message"       ) var message      : Message? = Message(),
    @SerializedName("finish_reason" ) var finishReason : String?  = null

)

fun convertJsonToContent(json: String): Content {
    val gson = Gson()
    return gson.fromJson(json, Content::class.java)
}

data class Usage (

    @SerializedName("prompt_tokens"     ) var promptTokens     : Int? = null,
    @SerializedName("completion_tokens" ) var completionTokens : Int? = null,
    @SerializedName("total_tokens"      ) var totalTokens      : Int? = null

)
data class Content (

    @SerializedName("name"         ) var name        : String?           = null,
    @SerializedName("language"     ) var language    : String?           = null,
    @SerializedName("image_prompt" ) var imagePrompt : String?           = null,
    @SerializedName("type"         ) var type        : String?           = null,
    @SerializedName("calories"     ) var calories    : String?           = null,
    @SerializedName("prepare_time" ) var time        : String?           = null,
    @SerializedName("ingredients"  ) var ingredients : ArrayList<String> = arrayListOf(),
    @SerializedName("steps"        ) var steps       : ArrayList<String> = arrayListOf()

)
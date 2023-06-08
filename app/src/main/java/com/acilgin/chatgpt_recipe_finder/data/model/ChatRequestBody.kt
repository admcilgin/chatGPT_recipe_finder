package com.acilgin.chatgpt_recipe_finder.data.model

import com.google.gson.annotations.SerializedName


data class ChatRequestBody (
    @SerializedName("model"    ) var model    : String?             = null,
    @SerializedName("messages" ) var messages : ArrayList<Messages> = arrayListOf()
)

data class Messages (

    @SerializedName("role"    ) var role    : String? = null,
    @SerializedName("content" ) var content : String? = null

)
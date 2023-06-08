package com.acilgin.chatgpt_recipe_finder.data.model

import com.google.gson.annotations.SerializedName

data class ImageRequestBody (

    @SerializedName("prompt" ) var prompt : String? = null,
    @SerializedName("n"      ) var n      : Int?    = null,
    @SerializedName("size"   ) var size   : String? = null

)
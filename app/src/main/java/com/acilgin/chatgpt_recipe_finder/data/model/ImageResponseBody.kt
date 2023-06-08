package com.acilgin.chatgpt_recipe_finder.data.model

import com.google.gson.annotations.SerializedName

class ImageResponseBody(

    @SerializedName("created") var created: Int? = null,
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()
)

data class Data(

    @SerializedName("url") var url: String? = null

)
package com.acilgin.chatgpt_recipe_finder.common.constants

import com.acilgin.chatgpt_recipe_finder.BuildConfig

object Constants {
    const val BASE_URL = "https://api.openai.com/v1/"

    // API TOKEN constant from local.properties
    const val API_TOKEN = BuildConfig.API_KEY
}
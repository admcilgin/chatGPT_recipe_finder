package com.acilgin.chatgpt_recipe_finder.di

import android.util.Log
import com.acilgin.chatgpt_recipe_finder.common.constants.Constants.API_TOKEN
import com.acilgin.chatgpt_recipe_finder.common.constants.Constants.BASE_URL
import com.acilgin.chatgpt_recipe_finder.common.utils.AuthInterceptor
import com.acilgin.chatgpt_recipe_finder.data.source.OpenAiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.math.log

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
     @Singleton
     fun provideOkHttpClient(): OkHttpClient {
         val loggingInterceptor = HttpLoggingInterceptor().apply {
             level = HttpLoggingInterceptor.Level.BODY
             // verbose logging
         }
        return OkHttpClient.Builder().addInterceptor { chain ->

             val newRequest: Request =
                 chain.request().newBuilder().addHeader("Content-Type", "application/json")
                     .addHeader("Authorization", "Bearer $API_TOKEN").build()
             chain.proceed(newRequest)
         }.addInterceptor(loggingInterceptor) .readTimeout(50, TimeUnit.SECONDS)
            .connectTimeout(50, TimeUnit.SECONDS).build()

     }



     @Provides
     @Singleton
     fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

     @Provides
     @Singleton
     fun provideRecipeService(retrofit: Retrofit): OpenAiService {
       return retrofit.create(OpenAiService::class.java)
    }
}
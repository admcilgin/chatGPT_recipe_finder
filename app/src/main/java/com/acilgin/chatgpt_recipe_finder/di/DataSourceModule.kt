package com.acilgin.chatgpt_recipe_finder.di

import com.acilgin.chatgpt_recipe_finder.data.source.OpenAiService
import com.acilgin.chatgpt_recipe_finder.data.source.RemoteDataSourceImpl
import com.acilgin.chatgpt_recipe_finder.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        openAiService: OpenAiService
    ): RemoteDataSource =
        RemoteDataSourceImpl(openAiService)

}
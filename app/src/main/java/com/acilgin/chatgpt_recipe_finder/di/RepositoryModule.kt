package com.acilgin.chatgpt_recipe_finder.di

import com.acilgin.chatgpt_recipe_finder.data.repository.ChatRepositoryImpl
import com.acilgin.chatgpt_recipe_finder.data.repository.CompletionRepositoryImpl
import com.acilgin.chatgpt_recipe_finder.data.repository.ImageRepositoryImpl
import com.acilgin.chatgpt_recipe_finder.data.source.RemoteDataSourceImpl
import com.acilgin.chatgpt_recipe_finder.domain.repository.ChatRepository
import com.acilgin.chatgpt_recipe_finder.domain.repository.CompletionRepository
import com.acilgin.chatgpt_recipe_finder.domain.repository.ImageRepository
import com.acilgin.chatgpt_recipe_finder.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

   @Provides
   @Singleton
   fun provideCompletionRepository(
       remoteDataSource: RemoteDataSource
   ): CompletionRepository =
         CompletionRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideChatRepository(
        remoteDataSource: RemoteDataSource
    ): ChatRepository =
        ChatRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideImageRepository(
        remoteDataSource: RemoteDataSource
    ): ImageRepository =
        ImageRepositoryImpl(remoteDataSource)
}
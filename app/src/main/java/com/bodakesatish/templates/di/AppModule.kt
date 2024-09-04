package com.bodakesatish.templates.di

import com.bodakesatish.data.prefs.SessionManager
import com.bodakesatish.data.prefs.SharedPreferencesManagerImpl
import com.bodakesatish.data.repository.SchemeRepositoryImpl
import com.bodakesatish.domain.repository.SchemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    internal abstract fun provideSchemeRepository(impl: SchemeRepositoryImpl): SchemeRepository


    @Binds
    internal abstract fun provideSharedPreferencesManager(sharedPreferencesManagerImpl: SharedPreferencesManagerImpl): SessionManager


}
package com.bodakesatish.templates.di

import com.bodakesatish.data.prefs.SessionManager
import com.bodakesatish.data.prefs.SharedPreferencesManagerImpl
import com.bodakesatish.data.repository.CustomerRepositoryImpl
import com.bodakesatish.data.repository.JobRepositoryImpl
import com.bodakesatish.data.repository.ServicesRepositoryImpl
import com.bodakesatish.data.repository.TemplateRepositoryImpl
import com.bodakesatish.domain.repository.CustomerRepository
import com.bodakesatish.domain.repository.BillRepository
import com.bodakesatish.domain.repository.ServicesRepository
import com.bodakesatish.domain.repository.TemplateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    internal abstract fun provideTemplateRepository(templateRepositoryImpl: TemplateRepositoryImpl): TemplateRepository

    @Binds
    internal abstract fun provideSharedPreferencesManager(sharedPreferencesManagerImpl: SharedPreferencesManagerImpl): SessionManager

    @Binds
    internal abstract fun provideServiceRepository(servicesRepositoryImpl: ServicesRepositoryImpl): ServicesRepository

    @Binds
    internal abstract fun provideCustomerRepository(customerRepositoryImpl: CustomerRepositoryImpl): CustomerRepository

    @Binds
    internal abstract fun provideJobRepository(jobRepositoryImpl: JobRepositoryImpl): BillRepository

}
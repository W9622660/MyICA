package com.example.myica.di

import com.example.myica.data.api.ApiConstants
import com.example.myica.data.api.PoliceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PoliceApiModule {

    @Provides
    @Singleton
    fun provideApi(builder:Retrofit.Builder): PoliceAPI{
        return builder
            .build()
            .create(PoliceAPI::class.java)
    }

    /**
     * Create retrofit object
    */
    @Provides
    @Singleton
    fun provideAPIRetrofit() : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_POLICE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}
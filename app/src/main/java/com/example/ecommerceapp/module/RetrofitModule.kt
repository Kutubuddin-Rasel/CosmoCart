package com.example.ecommerceapp.module

import com.example.ecommerceapp.api.RichMan
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://www.startech.com.bd/").build()
    }
    @Provides
    @Singleton
    fun provideRichManApi(retrofit: Retrofit):RichMan{
        return retrofit.create(RichMan::class.java)
    }
}
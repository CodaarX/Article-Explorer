package com.decagon.edconnect.di

import android.content.SharedPreferences
import com.decagon.edconnect.data.datasources.remoteSource.AuthenticationApiService
import com.decagon.edconnect.data.datasources.remoteSource.ProjectApiService
import com.decagon.edconnect.data.datasources.remoteSource.UserApiService
import com.decagon.edconnect.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLogger() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(sharedPreferences: SharedPreferences) : Interceptor {
            return Interceptor { chain ->
                val request = chain.request().newBuilder()
//                sharedPreferences.getString(TOKEN, null)?.let {
//                    request.addHeader("Authorization", "Bearer $it")
//                }
                chain.proceed(request.build())
            }
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        headerAuthorization: Interceptor,
        logger: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(headerAuthorization)
            .addInterceptor(logger)
//            .connectTimeout(timeOutSec, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun provideRetrofitService(client: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }


    @Singleton
    @Provides
    fun provideProjectApiService(retrofit: Retrofit): ProjectApiService {
        return retrofit.create(ProjectApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }


    @Singleton
    @Provides
    fun providesAuthenticationApiService(retrofit: Retrofit): AuthenticationApiService {
        return retrofit.create(AuthenticationApiService::class.java)
    }
}
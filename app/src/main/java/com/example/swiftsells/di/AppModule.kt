package com.example.swiftsells.di


import com.example.swiftsells.network.ProductsApi
import com.example.swiftsells.repository.ProductRepository
import com.example.swiftsells.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProductRepository(api: ProductsApi) = ProductRepository(api)

    @Singleton
    @Provides
    fun provideProductApi(): ProductsApi{
        //when called this would contain instance to our api and we can retrieve our data
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

}
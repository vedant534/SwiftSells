package com.example.swiftsells.network

import com.example.swiftsells.model.AddProductResponse
import com.example.swiftsells.model.ProductDetails
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import javax.inject.Singleton


@Singleton
interface ProductsApi {
    @GET("get")
    suspend fun getAllProducts(): ArrayList<ProductDetails>

    @POST("add")
    @Multipart
    suspend fun addProduct(
        @Part("product_name") productName: RequestBody,
        @Part("product_type") productType: RequestBody,
        @Part("price") price: RequestBody,
        @Part("tax") tax: RequestBody,
        @Part image: MultipartBody.Part?
    ): AddProductResponse
}
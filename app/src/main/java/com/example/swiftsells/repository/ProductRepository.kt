package com.example.swiftsells.repository

import android.util.Log
import com.example.swiftsells.model.ProductDetails
import com.example.swiftsells.network.ProductsApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductsApi
) {

    private var productItemsList = ArrayList<ProductDetails>()

    suspend fun getAllProducts(): ArrayList<ProductDetails> {
        productItemsList = api.getAllProducts()
        return productItemsList
    }

    suspend fun pushProduct(productDetails: ProductDetails) {
        val response = api.addProduct(
            productDetails.productName.toRequestBody("text/plain".toMediaTypeOrNull()),
            productDetails.productType.toRequestBody("text/plain".toMediaTypeOrNull()),
            productDetails.price.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            productDetails.tax.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            null // Left out
        )

        if (response.success) {
            // Product added successfully
            val addedProduct = response.productDetails
            Log.d("myTag", addedProduct.toString())
        } else {
            // Product addition failed
            val errorMessage = response.message
            Log.d("myTag", errorMessage)
        }
    }

}
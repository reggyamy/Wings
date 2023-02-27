package com.reggya.wings.data.network

import com.reggya.wings.data.model.PostResponse
import com.reggya.wings.data.model.ProductsResponse
import com.reggya.wings.data.model.Transaction
import com.reggya.wings.data.model.User
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.*

interface ApiService {

    @POST("login.json")
    fun login(@Body user: User): Flowable<PostResponse>

    @GET("product.json")
    fun getListProduct(): Flowable<ProductsResponse>

    @POST("transaction_header.json")
    fun addTransaction(@Body transaction: Transaction): Flowable<PostResponse>

}
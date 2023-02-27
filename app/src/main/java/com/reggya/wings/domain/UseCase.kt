package com.reggya.wings.domain

import com.reggya.wings.data.model.PostResponse
import com.reggya.wings.data.model.ProductItemResponse
import com.reggya.wings.data.model.Transaction
import com.reggya.wings.data.model.User
import com.reggya.wings.data.remote.ApiResponse
import io.reactivex.rxjava3.core.Flowable

interface UseCase {
    fun login(user: User): Flowable<ApiResponse<PostResponse>>
    fun getListProduct(): Flowable<ApiResponse<List<ProductItemResponse>>>
    fun addTransaction(transaction: Transaction): Flowable<ApiResponse<PostResponse>>
}

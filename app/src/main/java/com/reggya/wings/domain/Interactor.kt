package com.reggya.wings.domain

import com.reggya.wings.data.model.PostResponse
import com.reggya.wings.data.model.ProductItemResponse
import com.reggya.wings.data.model.Transaction
import com.reggya.wings.data.model.User
import com.reggya.wings.data.remote.ApiResponse
import io.reactivex.rxjava3.core.Flowable

class Interactor (private val iRepository: IRepository): UseCase{
    override fun login(user: User): Flowable<ApiResponse<PostResponse>> {
        return iRepository.login(user)
    }

    override fun getListProduct(): Flowable<ApiResponse<List<ProductItemResponse>>> {
        return iRepository.getListProduct()
    }

    override fun addTransaction(transaction: Transaction): Flowable<ApiResponse<PostResponse>> {
        return iRepository.addTransaction(transaction)
    }

}
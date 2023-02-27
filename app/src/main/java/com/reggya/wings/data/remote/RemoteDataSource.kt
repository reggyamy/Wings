package com.reggya.wings.data.remote

import android.annotation.SuppressLint
import android.util.Log
import com.reggya.wings.data.model.*
import com.reggya.wings.data.network.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService){

    @SuppressLint("LongLogTag")
    fun login(user: User): Flowable<ApiResponse<PostResponse>> {
        val resultData = PublishSubject.create<ApiResponse<PostResponse>>()

        val client = apiService.login(user)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(
                    ((if (response.name?.isNotEmpty() == true) ApiResponse.success(response)
                    else ApiResponse.empty()) as ApiResponse<PostResponse>?)
                )
            },{ throwable ->
                resultData.onNext(ApiResponse.error(throwable.message.toString()))
                Log.e("RemoteDataSource login", throwable.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("LongLogTag")
    fun getListProduct(): Flowable<ApiResponse<List<ProductItemResponse>>>{
        val result = PublishSubject.create<ApiResponse<List<ProductItemResponse>>>()

        val client = apiService.getListProduct()
        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                result.onNext(
                    (((if (!response.productResponse.isNullOrEmpty()) ApiResponse.success(response.productResponse)
                    else ApiResponse.empty()) as ApiResponse<List<ProductItemResponse>>?))
                )
            },{ throwable ->
                result.onNext(ApiResponse.error(throwable.message.toString()))
                Log.e("RemoteDataSource getListProduct", throwable.toString())
            })
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("LongLogTag")
    fun addTransaction(transaction: Transaction): Flowable<ApiResponse<PostResponse>>{
        val resultData = PublishSubject.create<ApiResponse<PostResponse>>()

        val client = apiService.addTransaction(transaction)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(
                    ((if (response.name?.isNotEmpty() == true) ApiResponse.success(response)
                    else ApiResponse.empty()) as ApiResponse<PostResponse>?)
                )
            },{ throwable ->
                resultData.onNext(ApiResponse.error(throwable.message.toString()))
                Log.e("RemoteDataSource addTransaction", throwable.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiService)
            }

    }
}
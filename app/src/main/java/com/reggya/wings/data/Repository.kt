package com.reggya.wings.data
import com.reggya.wings.data.model.PostResponse
import com.reggya.wings.data.model.ProductItemResponse
import com.reggya.wings.data.model.Transaction
import com.reggya.wings.data.model.User
import com.reggya.wings.data.remote.ApiResponse
import com.reggya.wings.data.remote.RemoteDataSource
import com.reggya.wings.domain.IRepository
import io.reactivex.rxjava3.core.Flowable

class Repository(
    private val remoteDataSource: RemoteDataSource
): IRepository {

    override fun login(user: User): Flowable<ApiResponse<PostResponse>> {
        return remoteDataSource.login(user)
    }

    override fun getListProduct(): Flowable<ApiResponse<List<ProductItemResponse>>> {
        return remoteDataSource.getListProduct()
    }

    override fun addTransaction(transaction: Transaction): Flowable<ApiResponse<PostResponse>> {
        return remoteDataSource.addTransaction(transaction)
    }

    companion object{
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource
        ): Repository =
            instance ?: synchronized(this){
                instance ?: Repository(remoteDataSource)
            }
    }
}
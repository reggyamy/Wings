package com.reggya.wings.di

import com.reggya.wings.data.Repository
import com.reggya.wings.data.network.ApiConfig
import com.reggya.wings.data.remote.RemoteDataSource
import com.reggya.wings.domain.IRepository
import com.reggya.wings.domain.Interactor
import com.reggya.wings.domain.UseCase

object Injection {

    private fun provideRepository(): IRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        return Repository.getInstance(remoteDataSource)
    }

    fun provideUseCase(): UseCase {
        val repository = provideRepository()
        return Interactor(repository)
    }
}
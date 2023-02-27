package com.reggya.wings.ui

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.reggya.wings.data.model.Transaction
import com.reggya.wings.data.model.User
import com.reggya.wings.domain.UseCase

class WingsViewModel(private val useCase: UseCase): ViewModel(){
    fun login(user: User) = LiveDataReactiveStreams.fromPublisher(useCase.login(user))
    fun getListProduct() = LiveDataReactiveStreams.fromPublisher(useCase.getListProduct())
    fun setTransactions(transaction: Transaction) = LiveDataReactiveStreams.fromPublisher(useCase.addTransaction(transaction))
}

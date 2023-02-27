package com.reggya.wings.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reggya.wings.di.Injection
import com.reggya.wings.domain.UseCase

class ViewModelFactory private constructor(
    private val useCase: UseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(WingsViewModel::class.java) ->{
                WingsViewModel(useCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }


        companion object{
            private val instance: ViewModelFactory? = null

            fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this){
                    instance ?: ViewModelFactory(Injection.provideUseCase())
                }
        }
}
package com.reggya.wings.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class User(
	val user: String,
	val password: String
): Parcelable

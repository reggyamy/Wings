package com.reggya.wings.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Transaction(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("document_code")
	val documentCode: String? = null,

	@field:SerializedName("user")
	val user: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
) : Parcelable
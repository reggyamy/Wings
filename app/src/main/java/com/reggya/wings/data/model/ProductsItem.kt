package com.reggya.wings.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductsItem(

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("sub_total")
	val subTotal: Int? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("product_code")
	val productCode: String? = null
) : Parcelable
package com.reggya.wings.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductsResponse(
	@field:SerializedName("-NPCe-uG3cCREG65-fGX")
	val productResponse: List<ProductItemResponse?>? = null
) : Parcelable

@Parcelize
data class ProductItemResponse(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("discount")
	val discount: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("product_code")
	val productCode: String? = null,

	@field:SerializedName("dimension")
	val dimension: String? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	var quantity: Int? = 0
) : Parcelable

package com.reggya.wings.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.reggya.wings.data.model.ProductItemResponse
import java.lang.reflect.Type


class OrderPreferences (context: Context) {

    companion object{
        private const val KEY_PREFS = "orders_pref"
        const val KEY_ADD = "add_to_cart"
    }
    private val preference = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)

    fun setData(array: ArrayList<ProductItemResponse>){
        val products = array.distinct()
        products.forEach { product ->
            product.quantity = array.count{ it == product }
        }
        setOrders(products)
    }

    private fun setOrders(products: List<ProductItemResponse>) {
        val editor = preference.edit()
        val gson = Gson()
        val json = gson.toJson(products)
        editor.putString(KEY_ADD, json)
        editor.apply()

    }

    fun getOrder(): ArrayList<ProductItemResponse>? {
        var arrayItems: ArrayList<ProductItemResponse>? = null
        val serializedObject: String? = preference.getString(KEY_ADD, null)
        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<List<ProductItemResponse>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)
        }
        return arrayItems
    }

    fun clearData() {
        preference.edit().remove(KEY_ADD).apply()
    }
}
package com.reggya.wings

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reggya.wings.data.model.ProductsItem
import com.reggya.wings.data.model.Transaction
import com.reggya.wings.data.remote.ApiResponseType
import com.reggya.wings.databinding.ActivityOrderBinding
import com.reggya.wings.ui.ViewModelFactory
import com.reggya.wings.ui.WingsViewModel
import com.reggya.wings.utils.LoginPreference
import com.reggya.wings.utils.OrderAdapter
import com.reggya.wings.utils.OrderPreferences
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class OrderActivity : AppCompatActivity(){
    private lateinit var binding: ActivityOrderBinding
    private lateinit var viewModel: WingsViewModel
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orders = OrderPreferences(this).getOrder()
        val total = kotlin.collections.ArrayList<Long>()
        if (orders!= null){
            val orderAdapter = OrderAdapter()
            orderAdapter.setData(orders)
            binding.rvProduct.layoutManager = LinearLayoutManager(this)
            binding.rvProduct.adapter = orderAdapter
            binding.rvProduct.setHasFixedSize(true)

            orders.forEach { product ->
                val totalPrice: Long? = if (product.discount != "0") {
                    val discount = product.discount?.toLong()?.let { product.price?.toLong()?.div(it) }
                    product.price?.toLong()?.minus(discount!!)?.times(product.quantity!!)
                }else product.price?.toLong()?.times(product.quantity!!)
                if (totalPrice != null) {
                    total.add(totalPrice)
                }
            }
            binding.totalPrice.text ="Rp "+ total.sum().toString()
            val time = Calendar.getInstance().time
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm")
            val current = formatter.format(time).slice(0..9)
            val products = ArrayList<ProductsItem>()
            for (i in orders.indices){
                val it = orders[i]
                val product = ProductsItem(
                    unit = it.unit,
                    quantity = it.quantity,
                    price = it.price?.toInt(),
                    subTotal = total[i].toInt(),
                    productCode = it.productCode,
                    currency = it.currency
                )
                products.add(product)
            }

            viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[WingsViewModel::class.java]
            binding.btCheckout.setOnClickListener {
                viewModel.setTransactions(
                    Transaction(
                        date = current,
                        user = LoginPreference(this).getUsername(),
                        total = total.sum().toInt(),
                        documentCode = "TRX",
                        products = products
                    )
                ).observe(this){
                    if (it.type == ApiResponseType.SUCCESS){
                        val preference = OrderPreferences(this)
                        preference.clearData()
                        finish()
                    }else Toast.makeText(this, "Checkout Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}
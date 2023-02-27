package com.reggya.wings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reggya.wings.data.remote.ApiResponseType
import com.reggya.wings.data.remote.ApiResponseType.*
import com.reggya.wings.databinding.ActivityMainBinding
import com.reggya.wings.ui.ViewModelFactory
import com.reggya.wings.ui.WingsViewModel
import com.reggya.wings.utils.ProductAdapter

class MainActivity : AppCompatActivity(), ProductAdapter.AddProductListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WingsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[WingsViewModel::class.java]
        val productAdapter = ProductAdapter()
        viewModel.getListProduct().observe(this){
            when(it.type){
                SUCCESS -> {
                    productAdapter.setData(it.data)
                }
                EMPTY -> Toast.makeText(this, "Fetch success but data null", Toast.LENGTH_SHORT).show()
                ERROR -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
        productAdapter.addListener = this
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        binding.rvProduct.adapter = productAdapter
        binding.rvProduct.setHasFixedSize(true)

        binding.btCart.setOnClickListener{
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }

    override fun addToCart(size: Int) {
        binding.containerQuantity.visibility = View.VISIBLE
        binding.quantity.text = size.toString()
    }

}
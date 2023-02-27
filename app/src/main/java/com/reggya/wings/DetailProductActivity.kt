package com.reggya.wings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.reggya.wings.data.model.ProductItemResponse
import com.reggya.wings.databinding.ActivityDetailProductBinding

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding
    private var totalPrice : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getParcelableExtra<ProductItemResponse>("detail_product")
        Glide.with(this)
            .load(product?.image)
            .into(binding.image)
        binding.name.text = product?.productName
        if (product?.discount != "0") {
            binding.containerPrice.visibility = View.VISIBLE
            binding.containerDisc.visibility = View.VISIBLE
            val discount = product?.discount?.toLong()?.let { product.price?.toLong()?.div(it) }
            totalPrice = product?.price?.toLong()?.minus(discount!!)
            binding.price.text = "Rp " + product?.price.toString()
        }else totalPrice = product.price?.toLong()
        binding.totalPrice.text = "Rp " + totalPrice.toString()
        binding.discount.text = product?.discount + "%"
        binding.dimension.text = product?.dimension
        binding.unit.text = product?.unit



    }
}
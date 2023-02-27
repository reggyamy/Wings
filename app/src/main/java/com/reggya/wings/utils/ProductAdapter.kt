package com.reggya.wings.utils

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reggya.wings.DetailProductActivity
import com.reggya.wings.data.model.ProductItemResponse
import com.reggya.wings.databinding.ProductItemBinding


class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var products = ArrayList<ProductItemResponse>()
    var addListener : AddProductListener? = null

    interface AddProductListener {
        fun addToCart(size: Int)
    }

    fun setData(newData: List<ProductItemResponse>?){
        if (newData == null) return
        products.clear()
        products.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(private val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
//        val array = OrderPreferences(itemView.context).getOrder()
        fun bind(product: ProductItemResponse) {
            val totalPrice: Long?
            Glide.with(itemView.context)
                .load(product.image)
                .into(binding.image)
            binding.name.text = product.productName
            if (product.discount != "0") {
                val discount = product.discount?.toLong()?.let { product.price?.toLong()?.div(it) }
                totalPrice = product.price?.toLong()?.minus(discount!!)
                binding.price.text = "Rp " + product.price.toString()
            }else totalPrice = product.price?.toLong()
            binding.price.text = "Rp " + totalPrice.toString()

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailProductActivity::class.java)
                intent.putExtra("detail_product", product)
                itemView.context.startActivity(intent)
            }
            val array = OrderPreferences(itemView.context).getOrder()
            binding.btAdd.setOnClickListener {
                if (array != null) {
                    array.add(product)
                    OrderPreferences(itemView.context).setData(array)
                    array.let { it1 -> addListener?.addToCart(it1.size) }
                }else {
                    val products = ArrayList<ProductItemResponse>()
                    products.add(product)
                    OrderPreferences(itemView.context).setData(products)
                    Toast.makeText(itemView.context, "Success add to cart", Toast.LENGTH_SHORT).show()
                    addListener?.addToCart(products.size) }
                }
            }
        }


}

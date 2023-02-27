package com.reggya.wings.utils

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reggya.wings.DetailProductActivity
import com.reggya.wings.data.model.ProductItemResponse
import com.reggya.wings.databinding.ItemOrderBinding

class OrderAdapter: RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var products = ArrayList<ProductItemResponse>()

    interface GetSubTotal {
        fun totals(totals: ArrayList<Long>)
    }

    fun setData(newData: List<ProductItemResponse>?){
        if (newData == null) return
        products.clear()
        products.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(private val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: ProductItemResponse) {
            Glide.with(itemView.context)
                .load(product.image)
                .into(binding.image)
            binding.name.text = product.productName
            val totalPrice: Long? = if (product.discount != "0") {
                val discount = product.discount?.toLong()?.let { product.price?.toLong()?.div(it) }
                product.price?.toLong()?.minus(discount!!)?.times(product.quantity!!)
            }else product.price?.toLong()?.times(product.quantity!!)
            binding.totalPrice.text = "Rp " + totalPrice.toString()
            binding.quantity.text = product.quantity.toString()
            binding.unit.text = product.unit

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailProductActivity::class.java)
                intent.putExtra("detail_product", product)
                itemView.context.startActivity(intent)
            }

        }

    }

}

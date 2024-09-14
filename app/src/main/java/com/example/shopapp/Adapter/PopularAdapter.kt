package com.example.shopapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.shopapp.Model.ItemModal
import com.example.shopapp.databinding.ViewholderRecommendedBinding

public class PopularAdapter(val items: List<ItemModal>): RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private val conext: Context? = null

    class ViewHolder(val binding: ViewholderRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder {
        val binding =
            ViewholderRecommendedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceText.text = "$" + items[position].price.toString()
        holder.binding.ratingTxt.text = items[position].rating.toString()
        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.pic)
    }

    override fun getItemCount(): Int = items.size

}

package com.example.shopapp.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.Model.BrandModel
import com.example.shopapp.R
import com.example.shopapp.databinding.ViewHolderBrandBinding

class BrandAdapter(val items: List<BrandModel>) :
    RecyclerView.Adapter<BrandAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class Viewholder(val binding: ViewHolderBrandBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.Viewholder {
        context = parent.context
        val binding = ViewHolderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title

        // Load image with Glide
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        // Handle click events properly using `holder.adapterPosition`
        holder.binding.root.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {  // Ensure valid position
                lastSelectedPosition = selectedPosition
                selectedPosition = currentPosition

                // Notify the adapter of item changes
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
        }

        // Set text color
        holder.binding.title.setTextColor(ContextCompat.getColor(context, R.color.white))

        // Handle selected state based on position
        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundResource(0)  // No background
            holder.binding.mainLayout.setBackgroundResource(R.drawable.intro_page_purple_buton)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white))
            )
            holder.binding.title.visibility = View.VISIBLE
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)  // Correct way to set drawable
            holder.binding.mainLayout.setBackgroundResource(0)  // No background
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.black))
            )
            holder.binding.title.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

package com.myproject.pixabayapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myproject.pixabayapp.data.categories.CategoryList
import com.myproject.pixabayapp.databinding.AddSourceItemBinding

class AddSourceAdapter(val list: List<CategoryList>): RecyclerView.Adapter<AddSourceAdapter.CategoryHolder>() {

    inner class CategoryHolder(var binding: AddSourceItemBinding): RecyclerView.ViewHolder(binding.root){
        var textCategory = binding.textView3
        var imageCategory = binding.imageView2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding = AddSourceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder((binding))
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.textCategory.text = holder.itemView.context.getString(list[position].categoryName)
        holder.imageCategory.setImageResource(list[position].categoryImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
package com.myproject.pixabayapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myproject.pixabayapp.data.ImageData
import com.myproject.pixabayapp.databinding.SearchItemViewBinding

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.ImageHolder>() {
    inner class ImageHolder(var binding: SearchItemViewBinding): RecyclerView.ViewHolder(binding.root)

    val diffUtil = object: DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }
    }
    val list = AsyncListDiffer(this,diffUtil)
    var searchAdapterList: List<ImageData>
    get() = list.currentList
    set(value) = list.submitList(value)

    private var onItemClickListener: ((List<ImageData>,position: Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (List<ImageData>, position: Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = SearchItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val imageList = searchAdapterList
        holder.binding.searchPage = searchAdapterList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(imageList, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchAdapterList.size
    }
}
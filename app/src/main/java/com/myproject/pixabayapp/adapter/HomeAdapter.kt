package com.myproject.pixabayapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myproject.pixabayapp.data.ImageData
import com.myproject.pixabayapp.databinding.HomePageItemBinding

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ImageHolder>() {
    inner class ImageHolder(var binding: HomePageItemBinding): RecyclerView.ViewHolder(binding.root)


    private val diffUtil = object: DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }
    }
    private val list = AsyncListDiffer(this,diffUtil)

    var homeAdapterList: List<ImageData>
        get() = list.currentList
        set(value) = list.submitList(value)
    private var onItemClickListener: ((ImageData) -> Unit)? = null

    fun setOnItemClickListener(listener: (ImageData) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = HomePageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val imageList = homeAdapterList[position]
        holder.binding.homePage = imageList
        holder.binding.saveImageButton.setOnClickListener {
            onItemClickListener?.let {
                it(imageList)
            }
        }
    }

    override fun getItemCount(): Int {
        return homeAdapterList.size
    }
}
package com.myproject.pixabayapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.myproject.pixabayapp.R
import com.myproject.pixabayapp.adapter.AddSourceAdapter
import com.myproject.pixabayapp.data.categories.CategoryList
import com.myproject.pixabayapp.databinding.FragmentAddSourceBinding
import com.myproject.pixabayapp.viewmodel.PixabayAppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSourceFragment : Fragment() {
    private lateinit var binding: FragmentAddSourceBinding
    private val viewModel: PixabayAppViewModel by viewModels()
    private lateinit var addSourceAdapter: AddSourceAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddSourceBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.layoutManager = GridLayoutManager(context, 3)
        addSourceAdapter = AddSourceAdapter(viewModel.categoryList)
        binding.recycler.adapter = addSourceAdapter
    }
}
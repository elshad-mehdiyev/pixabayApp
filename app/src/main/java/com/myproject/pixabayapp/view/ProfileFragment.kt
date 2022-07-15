package com.myproject.pixabayapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.myproject.pixabayapp.adapter.SearchAdapter
import com.myproject.pixabayapp.databinding.FragmentProfileBinding
import com.myproject.pixabayapp.viewmodel.PixabayAppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: PixabayAppViewModel by viewModels()
    private val searchAdapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSavedImageData()
        observeData()
        binding.recyclerProfile.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerProfile.adapter = searchAdapter
    }
    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.imageFromDB.observe(viewLifecycleOwner) {
                it?.let {
                    searchAdapter.searchAdapterList = it.reversed()
                }
            }
        }
    }
}
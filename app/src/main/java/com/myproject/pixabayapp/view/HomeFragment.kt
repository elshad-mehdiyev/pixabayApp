package com.myproject.pixabayapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.myproject.pixabayapp.adapter.HomeAdapter
import com.myproject.pixabayapp.constant.Status
import com.myproject.pixabayapp.databinding.FragmentHomeBinding
import com.myproject.pixabayapp.viewmodel.PixabayAppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: PixabayAppViewModel by viewModels()
    private val homeAdapter = HomeAdapter()
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchImage("everything")
        observeData()
        binding.recyclerHome.layoutManager = LinearLayoutManager(context)
        binding.recyclerHome.adapter = homeAdapter
        homeAdapter.setOnItemClickListener {
            job = CoroutineScope(Dispatchers.IO).launch {
                viewModel.insertDataIntoDB(it)
            }
        }
    }
    private fun observeData() {
        viewModel.imageFromApi.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled().let { result ->
                if (result != null) {
                    when(result.status) {
                        Status.SUCCESS -> {
                            val list = result.data?.hits?.shuffled()
                            homeAdapter.homeAdapterList = list ?: listOf()
                        }
                        Status.LOADING -> {

                        }
                        Status.ERROR -> {
                            Snackbar.make(
                                requireView(),
                                result.message ?: "An unknown error occured.",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }
}
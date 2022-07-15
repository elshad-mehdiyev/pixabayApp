package com.myproject.pixabayapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.myproject.pixabayapp.adapter.SearchAdapter
import com.myproject.pixabayapp.adapter.ViewPagerAdapter
import com.myproject.pixabayapp.constant.Constants
import com.myproject.pixabayapp.constant.Status
import com.myproject.pixabayapp.databinding.FragmentSearchBinding
import com.myproject.pixabayapp.viewmodel.PixabayAppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var job: Job? = null
    private val viewModel: PixabayAppViewModel by viewModels()
    private val searchAdapter = SearchAdapter()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private var idViewPager: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(Constants.ARG_OBJECT) }?.apply {
            idViewPager = getInt(Constants.ARG_OBJECT)
        }
        viewModel.searchImage("everything")
        binding.editText.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchImage(editable.toString())
                    }
                }
            }
        }
        observeData()
        binding.recycleSearch.layoutManager = GridLayoutManager(context, 3)
        binding.recycleSearch.adapter = searchAdapter
        searchAdapter.setOnItemClickListener { imagelist, position ->
            viewPagerAdapter = ViewPagerAdapter(this, imagelist)
            viewPager = binding.pager
            viewPager.adapter = viewPagerAdapter
            viewPager.currentItem = position
            viewPager.visibility = View.VISIBLE
            binding.recycleSearch.visibility = View.GONE
            binding.editTextTextPersonName.visibility = View.GONE
        }
    }
    private fun observeData() {
        viewModel.imageFromApi.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled().let { result ->
                if (result != null) {
                    when(result.status) {
                        Status.SUCCESS -> {
                            val list = result.data?.hits
                            searchAdapter.searchAdapterList = list ?: listOf()
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
}
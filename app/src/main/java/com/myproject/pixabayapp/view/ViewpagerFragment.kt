package com.myproject.pixabayapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.myproject.pixabayapp.constant.Constants.ARG_OBJECT
import com.myproject.pixabayapp.constant.Status
import com.myproject.pixabayapp.databinding.FragmentViewpagerBinding
import com.myproject.pixabayapp.viewmodel.PixabayAppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewpagerFragment : Fragment() {
    private lateinit var binding: FragmentViewpagerBinding
    private val viewModel: PixabayAppViewModel by viewModels()
    private var idViewPager: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewpagerBinding.inflate(inflater, container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            idViewPager = getInt(ARG_OBJECT)
        }
        viewModel.searchImage("everything")

        idViewPager?.let {
            if (it <= 20) observeData(it-1)
        }
    }
    private fun observeData(id: Int) {
        viewModel.imageFromApi.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled().let { result ->
                if (result != null) {
                    when(result.status) {
                        Status.SUCCESS -> {
                            binding.cardView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            val list = result.data?.hits
                            binding.homePage = list?.get(id)
                        }
                        Status.LOADING -> {
                            binding.cardView.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
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
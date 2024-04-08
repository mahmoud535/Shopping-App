package com.example.shoppingapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.common.util.Resource
import com.example.shoppingapp.data.model.Category2
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.presentation.adapter.HomeAdapter
import com.example.shoppingapp.presentation.viewmodel.HomeViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var adapter: HomeAdapter

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private var category2 = mutableListOf<Category2>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setObservers()
        setListeners()
        setInitialData()
    }

    private fun setObservers() {
        observeCategoriesList()
        observeProductsList()
    }

    private fun setListeners() {
        binding.apply {
            chipGroup.setOnCheckedChangeListener { group, checkedId ->
                val category = category2.getOrNull(checkedId)?.category ?: return@setOnCheckedChangeListener
                viewModel.getCategoryProducts(category)
            }

            homeCart.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
            }

            homeProfile.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }

            homeSearch.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }

            homeSearchView.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }
    }

    private fun setInitialData() {
        viewModel.getAllCategories()
        viewModel.getAllProducts()
        setUpRecyclerView()
    }

    private fun observeCategoriesList() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categories.collect { response ->
                    when (response) {
                        is Resource.Success -> { populateCategoryChips(response.data) }
                        is Resource.Loading -> { Log.i("HomeFragment", "Loading...")}
                        is Resource.Error -> { Log.i("HomeFragment", "${response.message}")}
                    }
                }
            }
        }
    }

    private fun populateCategoryChips(categories: List<String>?) {
        categories?.let { categoriesList ->
            binding.chipGroup.removeAllViews()
            category2.clear()
            val chipAll = Chip(requireContext())
            chipAll.text = "All"
            chipAll.id = 0
            chipAll.isChecked = true
            category2.add(Category2(0, "All"))
            binding.chipGroup.addView(chipAll)
            categoriesList.forEachIndexed { index, category ->
                val chip = Chip(requireContext())
                chip.text = category
                chip.id = index + 1
                category2.add(Category2(index, category))
                binding.chipGroup.addView(chip)
            }
        }
    }

    private fun observeProductsList() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            adapter.differ.submitList (response.data)
                            binding.homeRecyclerView.visibility = View.VISIBLE
                            Log.i("HomeFragment", "${response.data}")
                            binding.progressBar.visibility = View.GONE
                            binding.homeRecyclerView.visibility= View.VISIBLE
                        }
                        is Resource.Loading -> { binding.progressBar.visibility = View.VISIBLE
                                                 binding.homeRecyclerView.visibility= View.GONE
                            Log.i("HomeFragment", "Loading...") }
                        is Resource.Error -> { Log.i("HomeFragment", "${response.message}") }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.homeRecyclerView.adapter = adapter
        adapter.setOnItemClickListener { productId ->
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(productId)
            findNavController().navigate(action)
        }
    }
}
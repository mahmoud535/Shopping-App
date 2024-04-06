package com.example.shoppingapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentWishlistBinding
import com.example.shoppingapp.presentation.adapter.WishlistAdapter
import com.example.shoppingapp.presentation.viewmodel.WishlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private lateinit var binding : FragmentWishlistBinding
    private val  viewModel : WishlistViewModel by viewModels()
    private lateinit var adapter: WishlistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishlist,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWishlistBinding.bind(view)

        setUpRecyclerView()
        setListener()
        observer()
    }

    private fun observer(){
        lifecycleScope.launch {
            viewModel.getWishlist().collect { shopItems ->
                adapter.differ.submitList(shopItems)
            }
        }
    }

    private fun setListener(){
        binding.wishlistBack.setOnClickListener { findNavController().navigateUp() }

        adapter.setOnItemClickListener {
            val action = WishlistFragmentDirections.actionWishlistFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }

        adapter.setOnItemDeleteListener { viewModel.deleteWishlist(it) }

        binding.wishlistDelete.setOnClickListener { viewModel.clearWishlist() }
    }
    private fun setUpRecyclerView(){
        adapter = WishlistAdapter()
        binding.wishlistRecyclerView.adapter = adapter
    }

}
package com.example.shoppingapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentCartBinding
import com.example.shoppingapp.data.util.Utils
import com.example.shoppingapp.presentation.adapter.CartAdapter
import com.example.shoppingapp.presentation.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment(){

    @Inject
    lateinit var cartAdapter: CartAdapter

    private lateinit var binding : FragmentCartBinding
    private val cartViewModel: CartViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)

        actions()
    }

    private fun actions(){
        observeViewModel()
        setListeners()
        setupRecyclerView()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            cartViewModel.getCartItems().collect { cartItems ->
                cartAdapter.differ.submitList(cartItems)

                // Compute total items and total price
                val totalItems = cartItems.size
                val totalPrice = cartItems.sumOf { it.price.toDouble() }

                // Update UI with total items and total price
                binding.cartItemsInfo.text = "Total $totalItems Items"
                binding.cartItemsPrice.text = "USD ${Utils.formatPrice(totalPrice.toString())}"
            }
        }
    }

    private fun setListeners() {
        cartAdapter.setOnRemoveClickListener { cartItem ->
            cartViewModel.deleteCart(cartItem)
        }

        cartAdapter.incrementClickListener { cartItem ->
            cartViewModel.increment(cartItem)
        }

        cartAdapter.decrementClickListener { cartItem ->
            cartViewModel.decrement(cartItem)
        }

        binding.cartBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.cartClearAll.setOnClickListener {
            cartViewModel.clearCart()
        }
    }

    private fun setupRecyclerView(){
        binding.cartRecyclerView.adapter = cartAdapter
    }
}
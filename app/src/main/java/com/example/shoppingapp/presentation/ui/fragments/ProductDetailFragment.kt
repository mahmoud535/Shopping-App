package com.example.shoppingapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentProductDetailBinding
import com.google.android.material.snackbar.Snackbar
import com.example.shoppingapp.data.model.CartItem2
import com.example.shoppingapp.data.model.ShopItem
import com.example.shoppingapp.data.util.Utils
import com.example.shoppingapp.presentation.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()
    private lateinit var shopItem: ShopItem
    private var like = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shopItem = ProductDetailFragmentArgs.fromBundle(requireArguments()).shopItem
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)

        updateViews()
        listener()
    }

    private fun listener() {
        binding.apply {
            productDetailBack.setOnClickListener { findNavController().navigateUp() }
            productDetailAddToCart.setOnClickListener { addProductToCard() }
            productDetailCart.setOnClickListener { findNavController().navigate(R.id.action_productDetailFragment_to_cartFragment) }
            productDetailLike.setOnClickListener {
                if (like) removeProductToWishlist()
                else addProductToWishlist()
            }
        }
    }

    private fun addProductToWishlist() {
        like = true
        viewModel.addToWishlist(shopItem)
        binding.productDetailLike.setImageResource(R.drawable.ic_favorite)
        Snackbar.make(binding.productDetailAddToCart, "Added to wishlist", Snackbar.LENGTH_SHORT).show()
    }

    private fun removeProductToWishlist() {
        like = false
        viewModel.removeFromWishlist(shopItem)
        binding.productDetailLike.setImageResource(R.drawable.ic_not_favorite)
        Snackbar.make(binding.productDetailAddToCart, "Removed from wishlist", Snackbar.LENGTH_SHORT).show()
    }

    private fun addProductToCard() {
        val cartItem = CartItem2(
            shopItem.id, shopItem.image,
            Utils.formatPrice(shopItem.price.toString()), shopItem.title, 1, shopItem.price
        )
        viewModel.saveToCart(cartItem)
        Snackbar.make(binding.productDetailAddToCart, "Added to Cart Successfully", Snackbar.LENGTH_SHORT).show()
    }

    private fun updateViews() {
        binding.productDetailPrice.text = "USD ${shopItem.price}"
        binding.productDetailTitle.text = shopItem.title
        binding.productDetailDescription.text = shopItem.description
        Glide.with(binding.productDetailImage)
            .load(shopItem.image)
            .into(binding.productDetailImage)
        binding.productDetailRating.text = "${shopItem.rating.rate}"
        binding.productDetailReviews.text = "${shopItem.rating.count} Reviews"
    }
}
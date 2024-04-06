package com.example.shoppingapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import com.example.shoppingapp.data.model.User
import com.example.shoppingapp.common.util.Resource
import com.example.shoppingapp.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var binding: FragmentProfileBinding

    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        getUser()
        observeUserDetails()
        listener()
    }

    private fun getUser(){
        viewModel.getUser(2)
    }

    private fun observeUserDetails(){
        lifecycleScope.launch {
            viewModel.user.collect { result ->
                when (result) {
                    is Resource.Loading -> Log.i("ProfileFragment", "Loading...")
                    is Resource.Success -> {
                        user = result.data
                        binding.profileName.text =
                            "${result.data?.name?.firstname} ${result.data?.name?.lastname}"
                        binding.profileEmail.text = "${result.data?.email}"
                    }
                    is Resource.Error -> Log.i("ProfileFragment", "Error ${result.message}")
                }
            }
        }
    }

    private fun listener(){
        binding.apply {
            profileBack.setOnClickListener { findNavController().navigateUp() }

            profileEdit.setOnClickListener {
                Snackbar.make(binding.profileNotifications, "Coming soon...", Snackbar.LENGTH_SHORT).show()
            }

            profileNotifications.setOnClickListener {
                Snackbar.make(binding.profileNotifications, "Coming soon...", Snackbar.LENGTH_SHORT).show()
            }

            profileWishlist.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_wishlistFragment) }

            profileTerms.setOnClickListener {
                Snackbar.make(binding.profileNotifications, "Coming soon...", Snackbar.LENGTH_SHORT).show()
            }

            profilePrivacy.setOnClickListener {
                Snackbar.make(binding.profileNotifications, "Coming soon...", Snackbar.LENGTH_SHORT).show()
            }

            profileReportBug.setOnClickListener {
                Snackbar.make(binding.profileNotifications, "Coming soon...", Snackbar.LENGTH_SHORT).show()
            }

            profileLogout.setOnClickListener {
                viewModel.logoutUser()
                findNavController().navigate(R.id.action_profileFragment_to_splashFragment)
            }
        }
    }

}
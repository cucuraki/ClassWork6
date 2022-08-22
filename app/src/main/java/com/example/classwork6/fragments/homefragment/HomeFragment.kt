package com.example.classwork6.fragments.homefragment


import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.classwork6.databinding.HomeFragmentBinding
import com.example.classwork6.datastore.DataStore
import com.example.classwork6.fragments.basefragments.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {
    private var email: String = ""
    private var password: String = ""

    override fun listeners() {
        binding.logout.setOnClickListener {
            lifecycleScope.launch {
                DataStore.saveToken("")
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToLoginFragment(
                        email,
                        password
                    )
                )
            }
        }
    }

    override fun bindObservers() {
        lifecycleScope.launch {
            with(DataStore) {
                get("email")?.collect {
                    this@HomeFragment.email = it
                }
                get("password")?.collect {
                    this@HomeFragment.password = it
                }
            }
        }
    }

}
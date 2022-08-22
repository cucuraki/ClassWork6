package com.example.classwork6.fragments.registerfragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.classwork6.databinding.RegiterFragmentBinding
import com.example.classwork6.fragments.basefragments.BaseFragment
import com.example.classwork6.responsestate.ResponseState
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<RegiterFragmentBinding>(RegiterFragmentBinding::inflate) {
    private val model: RegisterViewModel by viewModels()

    override fun listeners() {
        with(binding){
            register.setOnClickListener {
                model.register(userName.text.toString(), password.text.toString())
            }
        }
    }

    override fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.processState.collect {
                    when (it) {
                        is ResponseState.Success -> {
                            if (it.body.token != "" && it.body.token != null) {
                                findNavController().navigate(
                                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(
                                        model.email, model.password
                                    )
                                )
                            }
                        }
                        is ResponseState.Error -> {
                            Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                        is ResponseState.Load -> {
                            //do something
                        }
                    }
                }
            }
        }
    }

}
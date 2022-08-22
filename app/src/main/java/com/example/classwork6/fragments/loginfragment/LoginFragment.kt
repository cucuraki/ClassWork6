package com.example.classwork6.fragments.loginfragment


import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.classwork6.databinding.LoginFragmentBinding
import com.example.classwork6.fragments.basefragments.BaseFragment
import com.example.classwork6.datastore.DataStore
import com.example.classwork6.responsestate.ResponseState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {
    private val model: LoginViewModel by viewModels()
    private val args: LoginFragmentArgs by navArgs()

    override fun listeners() {
        with(binding) {
            login.setOnClickListener {
                model.login(userName.text.toString(), password.text.toString())
            }
            register.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }

    }

    override fun bindObservers() {
        with(binding) {
            if (args.email != "  ") {
                userName.setText(args.email ?: "")
                password.setText(args.password ?: "")
            }
        }
        lifecycleScope.launch {
            DataStore.getToken?.collectLatest {
                if (it != "") {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    )
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.processState.collect {
                    when (it) {
                        is ResponseState.Success -> {
                            if (it.body.token != "") {
                                if (binding.remember.isChecked) {
                                    DataStore.saveToken(it.body.token)
                                } else
                                    findNavController().navigate(
                                        LoginFragmentDirections.actionLoginFragmentToHomeFragment()
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
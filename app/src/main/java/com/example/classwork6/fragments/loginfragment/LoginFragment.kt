package com.example.classwork6.fragments.loginfragment



import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.classwork6.databinding.LoginFragmentBinding
import com.example.classwork6.fragments.basefragments.BaseFragment
import com.example.classwork6.sharedpreference.SharedPreference


class LoginFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {
    private lateinit var sharedPref: SharedPreferences


    override fun listeners() {

    }

    override fun bindObservers() {
        if(SharedPreference.getToken()!=null){
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }


}
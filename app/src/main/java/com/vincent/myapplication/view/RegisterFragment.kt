package com.vincent.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.vincent.myapplication.R
import com.vincent.myapplication.databinding.FragmentRegisterBinding
import com.vincent.myapplication.model.User
import com.vincent.myapplication.viewModel.UserViewModel

class RegisterFragment : Fragment() {
    private lateinit var bind: FragmentRegisterBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        bind.btnRegister.setOnClickListener {
            val username = bind.txtUsername.text.toString()
            val pass = bind.txtPass.text.toString()
            val depan = bind.txtDepan.text.toString()
            val belakang = bind.txtBelakang.text.toString()
            val email = bind.txtEmail.text.toString()
            val user = User(username, pass, depan, belakang, email)

            viewModel.register(user)
            observeViewModel()
        }
    }

    fun observeViewModel(){
        viewModel.successLD.observe(viewLifecycleOwner, Observer {
            if (it != false){
                val action = RegisterFragmentDirections.signinFragmentAction()
                Navigation.findNavController(requireView()).navigate(action)
            }
            else{
                Toast.makeText(this.context, "Register failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
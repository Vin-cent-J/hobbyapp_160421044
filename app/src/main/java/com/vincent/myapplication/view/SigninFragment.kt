package com.vincent.myapplication.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.vincent.myapplication.R
import com.vincent.myapplication.databinding.FragmentSigninBinding
import com.vincent.myapplication.model.User
import com.vincent.myapplication.viewModel.BeritaListViewModel
import com.vincent.myapplication.viewModel.UserViewModel
import kotlin.math.log

class SigninFragment : Fragment() {

    private lateinit var bind: FragmentSigninBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentSigninBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        bind.btnLogin.setOnClickListener {
            val user = bind.txtUsername.text.toString()
            val pass = bind.txtPass.text.toString()

            if(user == "" || pass == ""){
                Toast.makeText(this.context, "Field is empty", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.signin(user, pass)
                observeViewModel()
            }
        }

        bind.btnNew.setOnClickListener {
            val action = SigninFragmentDirections.registerFragmentAction()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if (it != null){
                MainActivity.user = it
                val action = SigninFragmentDirections.homeFragmentAction()
                Navigation.findNavController(requireView()).navigate(action)
            }
            else{
                Toast.makeText(this.context, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
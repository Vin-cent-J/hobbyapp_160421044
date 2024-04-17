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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.vincent.myapplication.R
import com.vincent.myapplication.databinding.FragmentProfilBinding
import com.vincent.myapplication.model.User
import com.vincent.myapplication.viewModel.UserViewModel

class ProfilFragment : Fragment() {
    private lateinit var bind: FragmentProfilBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var navController: NavController
    var user: User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentProfilBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        user = MainActivity.user
        if (user != null) {
            bind.txtDepan.setText(user!!.nama_depan)
            bind.txtBelakang.setText(user!!.nama_belakang)

            bind.btnChange.setOnClickListener {
                if(bind.txtCurrentPass.text.toString() == user?.password){
                    user?.nama_depan=bind.txtDepan.text.toString()
                    user?.nama_belakang=bind.txtDepan.text.toString()
                    if(bind.txtNewPass.text.toString() != ""){
                        user?.password=bind.txtNewPass.text.toString()
                    }

                    viewModel.update(user!!)
                    observeViewModel()
                }
                else{
                    Toast.makeText(this.context, "Wrong password", Toast.LENGTH_SHORT).show()
                }
            }

        }
        bind.btnLogout.setOnClickListener {
            MainActivity.user = null
            Navigation.findNavController(it).navigateUp()
        }

    }

    fun observeViewModel(){
        viewModel.successLD.observe(viewLifecycleOwner, Observer {
            if (it != false){
                Toast.makeText(this.context, "Profile updated", Toast.LENGTH_SHORT).show()
                MainActivity.user = user
            }
            else{
                Toast.makeText(this.context, "Update failed", Toast.LENGTH_SHORT).show()
            }
        })
    }


}
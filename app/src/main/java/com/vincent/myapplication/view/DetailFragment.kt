package com.vincent.myapplication.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.vincent.myapplication.R
import com.vincent.myapplication.databinding.FragmentDetailBinding
import com.vincent.myapplication.databinding.FragmentHomeBinding
import com.vincent.myapplication.model.Berita
import com.vincent.myapplication.model.User
import com.vincent.myapplication.viewModel.BeritaListViewModel

class DetailFragment : Fragment() {
    private lateinit var bind: FragmentDetailBinding
    private lateinit var viewModel: BeritaListViewModel
    private val user = User("ABC", "ABC", "ABC", "ABC", "ABC")
    private lateinit var id:Number
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return bind.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            id = DetailFragmentArgs.fromBundle(requireArguments()).idBerita
        }
        viewModel = ViewModelProvider(this).get(BeritaListViewModel::class.java)
        viewModel.detail(id)

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            Log.d("Data", it.toString())
            val berita = it[0]
            bind.txtTitle.text = berita.title
            bind.txtUser.text = "@"+berita.username

            bind.btnBack.isEnabled = false

            var page = 0
            bind.txtDesc.text = berita.pages[page]

            bind.btnNext.setOnClickListener {
                page++
                bind.btnBack.isEnabled = true
                Log.d("Berita pages",berita.pages[page] )
                bind.txtDesc.text = berita.pages[page]
                if(page >= berita.pages.size-1){
                    bind.btnNext.isEnabled = false
                }
            }

            bind.btnBack.setOnClickListener {
                page--
                bind.btnNext.isEnabled = true
                bind.txtDesc.text = berita.pages[page]
                if(page <= 0){
                    bind.btnBack.isEnabled = false
                }
            }

            val picasso = Picasso.Builder(bind.root.context)
            picasso.listener{picasso, uri, exception -> exception.printStackTrace()}
            picasso.build().load(berita.url).into(bind.imgBerita)

        })
    }
}
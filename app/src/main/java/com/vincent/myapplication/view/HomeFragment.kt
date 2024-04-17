package com.vincent.myapplication.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vincent.myapplication.R
import com.vincent.myapplication.databinding.FragmentHomeBinding
import com.vincent.myapplication.model.Berita
import com.vincent.myapplication.model.User
import com.vincent.myapplication.viewModel.BeritaListViewModel


class HomeFragment : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private val beritas = arrayListOf<Berita>()
    private val adapter = BeritaListAdapter(beritas)
    private lateinit var viewModel : BeritaListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BeritaListViewModel::class.java)
        viewModel.refresh()

        observeViewModel()

        bind.recyclerView.layoutManager = LinearLayoutManager(context)
        bind.recyclerView.adapter = adapter
    }

    fun observeViewModel(){
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            Log.d("Data", it.toString())
            adapter.updateBerita(it)
        })
    }

}
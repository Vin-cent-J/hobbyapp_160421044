package com.vincent.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vincent.myapplication.databinding.BeritaListItemBinding
import com.vincent.myapplication.model.Berita

class BeritaListAdapter(val beritaList: ArrayList<Berita>): RecyclerView.Adapter<BeritaListAdapter.BeritaViewHolder>() {
    class BeritaViewHolder(val bind: BeritaListItemBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val bind = BeritaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        holder.bind.txtTitle.text = beritaList[position].title
        holder.bind.txtUser.text = "@"+beritaList[position].username
        holder.bind.txtDesc.text = beritaList[position].desc
        val id = beritaList[position].id

        val picasso = Picasso.Builder(holder.bind.root.context)
        picasso.listener{picasso, uri, exception -> exception.printStackTrace()}
        picasso.build().load(beritaList[position].url).into(holder.bind.imgBerita)

        holder.bind.btnDetail.setOnClickListener {
            val action = HomeFragmentDirections.detailFragmentAction(id)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun updateBerita(newBeritaList: ArrayList<Berita>){
        beritaList.clear()
        beritaList.addAll(newBeritaList)
        notifyDataSetChanged()
    }
}
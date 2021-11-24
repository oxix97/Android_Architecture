package com.example.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.ItemData
import com.example.myapplication.databinding.ItemTestViewBinding

class ListRecyclerViewAdapter(
    val viewModel: MainViewModel,
    val arrayList: ArrayList<ItemData>,
    val context: Context
) : RecyclerView.Adapter<ListRecyclerViewAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val binding: ItemTestViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemData) {
            binding.title.text = data.title
            binding.delete.setOnClickListener {
                viewModel.remove(data)
                notifyItemRemoved(arrayList.indexOf(data))
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListRecyclerViewAdapter.MainViewHolder {
        val binding =
            ItemTestViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListRecyclerViewAdapter.MainViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "list is empty", Toast.LENGTH_SHORT).show()
        }
        return arrayList.size
    }
}
package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapter.ListRecyclerViewAdapter
import com.example.myapplication.Adapter.MainViewModel
import com.example.myapplication.Adapter.ViewModelFactory
import com.example.myapplication.data.ItemData
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var manager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.btnSubmit.setOnClickListener {
            addData()
        }
        initialiseAdapter()
    }

    private fun initialiseAdapter() {
        binding.rvContainer.layoutManager = manager
        observeData()
    }


    private fun observeData() {
        viewModel.oldList.observe(this, Observer {
            Log.i("data", it.toString())
            binding.rvContainer.adapter = ListRecyclerViewAdapter(viewModel, it, this)
        })
    }

    private fun addData() {
        var text = binding.etInputText
        var title = text.text.toString()
        if (title.isBlank()) {
            Toast.makeText(this, "Enter value!!", Toast.LENGTH_SHORT).show()
        } else {
            var data = ItemData(title)
            viewModel.add(data)
            binding.etInputText.text.clear()
            binding.rvContainer.adapter?.notifyDataSetChanged()
        }
    }
}
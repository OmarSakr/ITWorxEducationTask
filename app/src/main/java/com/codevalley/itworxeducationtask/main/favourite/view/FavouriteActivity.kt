package com.codevalley.itworxeducationtask.main.favourite.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itworxeducationtask.databinding.ActivityFavouriteBinding
import com.codevalley.itworxeducationtask.main.favourite.adapter.FavouriteAdapter
import com.codevalley.itworxeducationtask.main.favourite.viewModel.FavouriteViewModel
import com.codevalley.itworxeducationtask.main.favourite.viewModel.FavouriteViewModelFactory
import com.codevalley.itworxeducationtask.utils.AppController
import com.codevalley.itworxeducationtask.utils.ParentClass

class FavouriteActivity : ParentClass() {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var favouriteAdapter: FavouriteAdapter

    private lateinit var favouriteViewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initUi()
    }

    private fun initUi() {
        favouriteViewModel = ViewModelProvider(
            this, FavouriteViewModelFactory((application as AppController).repository)
        ).get(FavouriteViewModel::class.java)
        favouriteViewModel.allWords.observe(this, {
            favouriteAdapter.addAll(it)
            favouriteAdapter.notifyDataSetChanged()
        })
        initRecycler()
    }

    private fun initRecycler() {
        favouriteAdapter = FavouriteAdapter(this)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvFavourite.layoutManager = linearLayoutManager
        binding.rvFavourite.adapter = favouriteAdapter

    }
}
package com.codevalley.itworxeducationtask.main.searchResults.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itworxeducationtask.databinding.ActivitySearchResultsBinding
import com.codevalley.itworxeducationtask.main.favourite.viewModel.FavouriteViewModel
import com.codevalley.itworxeducationtask.main.favourite.viewModel.FavouriteViewModelFactory
import com.codevalley.itworxeducationtask.main.home.adapter.HomeAdapter
import com.codevalley.itworxeducationtask.main.searchResults.viewModel.SearchResultsViewModel
import com.codevalley.itworxeducationtask.utils.AppController
import com.codevalley.itworxeducationtask.utils.ParentClass
import kotlinx.coroutines.flow.collectLatest

class SearchResultsActivity : ParentClass() {
    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var searchResultsViewModel: SearchResultsViewModel
    private lateinit var favouriteViewModel: FavouriteViewModel

    private var called = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initUi()
    }

    private fun initUi() {
        searchResultsViewModel = ViewModelProvider(this).get(SearchResultsViewModel::class.java)

        favouriteViewModel = ViewModelProvider(
            this, FavouriteViewModelFactory((application as AppController).repository)
        ).get(FavouriteViewModel::class.java)

        getSearchResults()
        initRecycler()
        checkLoadingState()
    }

    private fun initRecycler() {
        homeAdapter = HomeAdapter(this, favouriteViewModel)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvSearchResults.layoutManager = linearLayoutManager
        binding.rvSearchResults.adapter = homeAdapter

    }

    private fun getSearchResults() {
        lifecycleScope.launchWhenStarted {
            searchResultsViewModel.getSearchResults(
                sharedPrefManager!!.getUserDate().country,
                intent.getStringExtra("category")!!,
                getLang(this@SearchResultsActivity),
                intent.getStringExtra("searchWord")!!
            )
                .collectLatest {
                    homeAdapter.submitData(it)
                }
        }
    }

    private fun checkLoadingState() {
        homeAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
                called = true
            } else {
                if (called) {
                    binding.progressBar.visibility = View.GONE
                }
            }


        }
    }
}
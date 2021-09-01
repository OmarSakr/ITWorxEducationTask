package com.codevalley.itworxeducationtask.main.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itworxeducationtask.databinding.ActivityHomeBinding
import com.codevalley.itworxeducationtask.main.home.adapter.HomeAdapter
import com.codevalley.itworxeducationtask.main.home.viewModel.HomeViewModel
import com.codevalley.itworxeducationtask.splashAndOnBoarding.splash.view.SplashActivity
import com.codevalley.itworxeducationtask.utils.ParentClass
import kotlinx.coroutines.flow.collectLatest

class HomeActivity : ParentClass() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    var called = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initUi()
        initEventDriven()
    }

    private fun initEventDriven() {
        binding.tvFirstCategory.setOnClickListener {
            getTopHeadlines(sharedPrefManager!!.getUserDate().firstCategory)
        }
        binding.tvSecondCategory.setOnClickListener {
            getTopHeadlines(sharedPrefManager!!.getUserDate().secondCategory)
        }
        binding.tvThirdCategory.setOnClickListener {
            getTopHeadlines(sharedPrefManager!!.getUserDate().thirdCategory)
        }
        binding.ivLanguage.setOnClickListener {
            if (getLang(this@HomeActivity) == "ar") {
                storeLang("en", this@HomeActivity)
                val intent = Intent(this@HomeActivity, SplashActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)

            } else {
                storeLang("ar", this@HomeActivity)
                val intent = Intent(this@HomeActivity, SplashActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)

            }
        }
        binding.ivLike.setOnClickListener {

        }
        binding.ivSearch.setOnClickListener {

        }

    }

    private fun initUi() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        getTopHeadlines(sharedPrefManager!!.getUserDate().firstCategory)
        initRecycler()
        checkLoadingAndErrorState()
        initializeCategories()
    }

    private fun initRecycler() {
        homeAdapter = HomeAdapter(this)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvHome.layoutManager = linearLayoutManager
        binding.rvHome.adapter = homeAdapter

    }

    private fun getTopHeadlines(category: String) {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launchWhenStarted {
            homeViewModel.getTopHeadlines(
                sharedPrefManager!!.getUserDate().country,
                category, getLang(this@HomeActivity)
            )
                .collectLatest {
                    homeAdapter.submitData(it)
                }
        }
    }

    private fun initializeCategories() {
        binding.tvFirstCategory.text = sharedPrefManager?.getUserDate()?.firstCategory
        binding.tvSecondCategory.text = sharedPrefManager?.getUserDate()?.secondCategory
        binding.tvThirdCategory.text = sharedPrefManager?.getUserDate()?.thirdCategory
    }

    private fun checkLoadingAndErrorState() {
        homeAdapter.addLoadStateListener { loadState ->

            Log.e("loadState.refresh", loadState.refresh.toString());
            if (loadState.refresh is LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
                called = true
            } else {
                if (called) {
                    binding.progressBar.visibility = View.GONE
                }
                // getting the error

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                Log.e("error",error.toString())
                error?.let {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                }
            }


        }
    }
}
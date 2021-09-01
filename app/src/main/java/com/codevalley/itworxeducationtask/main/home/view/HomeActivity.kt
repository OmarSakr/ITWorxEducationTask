package com.codevalley.itworxeducationtask.main.home.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itworxeducationtask.R
import com.codevalley.itworxeducationtask.databinding.ActivityHomeBinding
import com.codevalley.itworxeducationtask.main.favourite.view.FavouriteActivity
import com.codevalley.itworxeducationtask.main.favourite.viewModel.FavouriteViewModel
import com.codevalley.itworxeducationtask.main.favourite.viewModel.FavouriteViewModelFactory
import com.codevalley.itworxeducationtask.main.home.adapter.HomeAdapter
import com.codevalley.itworxeducationtask.main.home.viewModel.HomeViewModel
import com.codevalley.itworxeducationtask.main.searchResults.view.SearchResultsActivity
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.repository.OnBoardingRepository
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.viewModel.OnBoardingViewModel
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.viewModel.OnBoardingViewModelFactory
import com.codevalley.itworxeducationtask.splashAndOnBoarding.splash.view.SplashActivity
import com.codevalley.itworxeducationtask.utils.AppController
import com.codevalley.itworxeducationtask.utils.ParentClass
import kotlinx.coroutines.flow.collectLatest

class HomeActivity : ParentClass() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var homeAdapter: HomeAdapter
    private var called = false
    private var categoryName = ""
    private lateinit var onBoardingViewModel: OnBoardingViewModel
    private lateinit var onBoardingRepository: OnBoardingRepository
    private lateinit var favouriteViewModel: FavouriteViewModel


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
            val intent = Intent(this@HomeActivity, FavouriteActivity::class.java)
            startActivity(intent)
        }
        binding.ivSearch.setOnClickListener {
            if (TextUtils.isEmpty(binding.etSearch.text.toString())) {
                binding.etSearch.error =
                    this@HomeActivity.getString(R.string.searchSpecificTopic)
            } else {
                val intent = Intent(this@HomeActivity, SearchResultsActivity::class.java)
                intent.putExtra("searchWord", binding.etSearch.text.toString())
                intent.putExtra("category", categoryName)
                startActivity(intent)
            }
        }

    }

    private fun initUi() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        dismiss_keyboard()
        onBoardingRepository = OnBoardingRepository()
        onBoardingViewModel = ViewModelProvider(
            this, OnBoardingViewModelFactory(onBoardingRepository)
        ).get(OnBoardingViewModel::class.java)

        favouriteViewModel = ViewModelProvider(
            this, FavouriteViewModelFactory((application as AppController).repository)
        ).get(FavouriteViewModel::class.java)

        onBoardingViewModel.getCategories()
        fillCategories()
        getTopHeadlines(sharedPrefManager!!.getUserDate().firstCategory)
        initRecycler()
        checkLoadingState()
        initializeCategories()
    }

    private fun initRecycler() {
        homeAdapter = HomeAdapter(this, favouriteViewModel)
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

    private fun fillCategories() {
        val countriesListAdapter: ArrayAdapter<String> = object :
            ArrayAdapter<String>(
                this@HomeActivity,
                R.layout.text_spinner,
                onBoardingViewModel.getCategorieslist()
            ) {

            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.parseColor("#000000"))
                } else {
                    tv.setTextColor(Color.parseColor("#000000"))
                }
                return view
            }
        }

        // Drop down layout style
        countriesListAdapter.setDropDownViewResource(R.layout.text_spinner)

        // attaching data adapter to spinner
        binding.spCategories.adapter = countriesListAdapter
        binding.spCategories.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    categoryName = onBoardingViewModel.getCategorieslist()[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        if (sharedPrefManager!!.getUserDate().firstCategory != "") {
            val spinnerPosition: Int =
                countriesListAdapter.getPosition(sharedPrefManager!!.getUserDate().firstCategory)
            binding.spCategories.setSelection(spinnerPosition)
        }
    }

}
package com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itworxeducationtask.R
import com.codevalley.itworxeducationtask.databinding.ActivityOnboardingBinding
import com.codevalley.itworxeducationtask.main.home.view.HomeActivity
import com.codevalley.itworxeducationtask.models.userModel.UserModel
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.adapter.OnBoardingAdapter
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.repository.OnBoardingRepository
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.viewModel.OnBoardingViewModel
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.viewModel.OnBoardingViewModelFactory
import com.codevalley.itworxeducationtask.utils.ParentClass

class OnBoardingActivity : ParentClass() {
    private lateinit var binding: ActivityOnboardingBinding
    var countryName = ""
    var countryId = "0"
    private lateinit var chosenList: MutableList<String>

    private lateinit var onBoardingAdapter: OnBoardingAdapter

    private lateinit var onBoardingViewModel: OnBoardingViewModel
    private lateinit var onBoardingRepository: OnBoardingRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initUi()
        initEventDriven()
    }

    private fun initEventDriven() {
        binding.tvSending.setOnClickListener {
            var cancel = false

            if (countryId == "0") {
                cancel = true
                makeToast(this@OnBoardingActivity, "please choose the country")
            }
            if (chosenList.size != 3) {
                cancel = true
                makeToast(this@OnBoardingActivity, "please choose three categories")
            }
            if (!cancel) {
                sharedPrefManager?.setFirstTime(false)
                val userModel = UserModel(countryId, chosenList[0], chosenList[1], chosenList[2])
                sharedPrefManager?.setUserDate(userModel)
                val intent = Intent(this@OnBoardingActivity, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)

            }
        }
    }

    private fun initUi() {
        chosenList = ArrayList()
        onBoardingRepository = OnBoardingRepository()
        onBoardingViewModel = ViewModelProvider(
            this, OnBoardingViewModelFactory(onBoardingRepository)
        ).get(OnBoardingViewModel::class.java)
        initRecycler()
        onBoardingViewModel.getCategories()
        onBoardingViewModel.getCountries()
        fillCountries()
        fillCategories()
    }

    private fun initRecycler() {
        onBoardingAdapter = OnBoardingAdapter(this, chosenList)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvCategories.layoutManager = linearLayoutManager
        binding.rvCategories.adapter = onBoardingAdapter

    }

    private fun fillCountries() {
        val countriesListAdapter: ArrayAdapter<String> = object :
            ArrayAdapter<String>(
                this@OnBoardingActivity,
                R.layout.text_spinner,
                onBoardingViewModel.getCountriesNameslist()
            ) {
            override fun getView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val v = super.getView(position, convertView, parent)
                if ((v as TextView).text.toString() == "Choose your country") {
                    v.setTextColor(Color.parseColor("#000000"))
                } else {
                    v.setTextColor(Color.parseColor("#000000"))
                }
                return v
            }

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


        // Drop down layout style
        countriesListAdapter.setDropDownViewResource(R.layout.text_spinner)

        // attaching data adapter to spinner
        binding.spCountries.adapter = countriesListAdapter
        binding.spCountries.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    countryName = onBoardingViewModel.getCountriesNameslist()[position]
                    countryId = onBoardingViewModel.getCountriesIdslist()[position]
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

    private fun fillCategories() {
        onBoardingAdapter.addAll(onBoardingViewModel.getCategorieslist())
        onBoardingAdapter.notifyDataSetChanged()
    }
}
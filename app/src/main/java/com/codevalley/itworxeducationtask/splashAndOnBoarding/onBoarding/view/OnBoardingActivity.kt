package com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.view

import android.os.Bundle
import android.view.View
import com.codevalley.itworxeducationtask.R
import com.codevalley.itworxeducationtask.databinding.ActivityOnboardingBinding
import com.codevalley.itworxeducationtask.utils.ParentClass

class OnBoardingActivity : ParentClass() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initUi()
        initEventDriven()
    }

    private fun initEventDriven() {


    }

    private fun initUi() {


    }
}
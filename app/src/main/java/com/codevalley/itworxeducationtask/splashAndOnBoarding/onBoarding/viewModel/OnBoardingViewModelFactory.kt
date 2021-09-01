package com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.repository.OnBoardingRepository

@Suppress("UNCHECKED_CAST")
class OnBoardingViewModelFactory(private val onBoardingRepository: OnBoardingRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnBoardingViewModel::class.java)) {
            return OnBoardingViewModel(onBoardingRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
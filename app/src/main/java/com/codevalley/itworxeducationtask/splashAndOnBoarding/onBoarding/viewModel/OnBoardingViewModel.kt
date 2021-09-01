package com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.viewModel

import androidx.lifecycle.ViewModel
import com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.repository.OnBoardingRepository

class OnBoardingViewModel(private val onBoardingRepository: OnBoardingRepository) : ViewModel() {

    fun getCountries() = onBoardingRepository.fillCountries()

    fun getCategories() = onBoardingRepository.fillCategories()

    fun getCountriesNameslist() = onBoardingRepository.countriesNames
    fun getCountriesIdslist() = onBoardingRepository.countriesIds
    fun getCategorieslist() = onBoardingRepository.categoriesNames

}
package me.dwidar.etmana_jet_compose.onBoardingPages.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.dwidar.etmana_jet_compose.onBoardingPages.OnBoardingEnum

class OnBoardingViewModel : ViewModel()
{
    private val onBoardPages : MutableLiveData<OnBoardingEnum> = MutableLiveData()

    init {
        onBoardPages.value = OnBoardingEnum.Page1
    }

    fun getOnBoardingPage() : LiveData<OnBoardingEnum> = onBoardPages

    fun changeOnBoardPage(enum: OnBoardingEnum)
    {
        onBoardPages.value = enum
    }
}
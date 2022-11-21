package me.dwidar.etmana_jet_compose.onBoardingPages.view

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import me.dwidar.etmana_jet_compose.R
import me.dwidar.etmana_jet_compose.onBoardingPages.OnBoardingEnum
import me.dwidar.etmana_jet_compose.onBoardingPages.viewModel.OnBoardingViewModel

class OnBoardActivity : ComponentActivity()
{
    private lateinit var onBoardingViewModel: OnBoardingViewModel
    private var onBoardingEnum = mutableStateOf(OnBoardingEnum.Page1)
    private lateinit var myContext : Context

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        myContext = this
        onBoardingViewModel = ViewModelProvider(this)[OnBoardingViewModel::class.java]

        onBoardingViewModel.getOnBoardingPage().observe(this){
            onBoardingEnum.value = it
        }

        setContent {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            )
            {
                logoImage()

                when(onBoardingEnum.value)
                {
                    OnBoardingEnum.Page1 -> page1(onChangePage = {goToPage(OnBoardingEnum.Page2)})
                    OnBoardingEnum.Page2 -> page2(onChangePage = {goToPage(OnBoardingEnum.Page3)})
                    OnBoardingEnum.Page3 -> page3(context = myContext)
                }
            }
        }
    }

    private fun goToPage(enum: OnBoardingEnum)
    {
        println(enum.name)
        onBoardingViewModel.changeOnBoardPage(enum)
    }
}

@Composable
fun logoImage()
{
    Box(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .fillMaxHeight(0.13f),
        contentAlignment = Alignment.Center)
    {
        val painter = rememberAsyncImagePainter(R.drawable.elogo)
        val state = painter.state
        Image(painter = painter, contentDescription = "logo image")
        if (state is AsyncImagePainter.State.Loading)
        {
            CircularProgressIndicator()
        }
    }
}

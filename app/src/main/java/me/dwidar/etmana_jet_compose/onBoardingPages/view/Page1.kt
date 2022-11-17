package me.dwidar.etmana_jet_compose.onBoardingPages.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import me.dwidar.etmana_jet_compose.R
import me.dwidar.etmana_jet_compose.onBoardingPages.OnBoardingEnum

@Composable
fun page1(onChangePage: (onBoardingEnum: OnBoardingEnum) -> Unit)
{
    Column (horizontalAlignment = Alignment.CenterHorizontally){

        onBoardImage1()

        Text(text = "Start", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.fillMaxHeight(0.14f))

        Text(text = "Begin your smooth shopping journey in every\nstep.", color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

            Button(
                modifier = Modifier.fillMaxWidth(0.46f).fillMaxHeight(0.6f),
                onClick = {
                    onChangePage(OnBoardingEnum.Page2) },
                shape = RoundedCornerShape(
                topStart = 24.dp,
                bottomStart = 24.dp
            ), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFED278D))) {

                Text(text = "Next",
                    color = Color.White, fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

            }

        }
    }
}

@Composable
fun onBoardImage1()
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        contentAlignment = Alignment.CenterEnd
        )
    {
        val painter = rememberAsyncImagePainter(R.drawable.board1)
        val state = painter.state
        Image(painter = painter, contentDescription = "logo image")
        if (state is AsyncImagePainter.State.Loading)
        {
            CircularProgressIndicator()
        }
    }
}


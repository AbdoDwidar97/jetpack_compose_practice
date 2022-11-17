package me.dwidar.etmana_jet_compose.onBoardingPages.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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


@Composable
fun page3()
{
    Column (horizontalAlignment = Alignment.CenterHorizontally){

        onBoardImage3()

        Text(text = "Hassle Free", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.fillMaxHeight(0.14f))

        Text(text = "You don't have to worry about exchange and\nrefund. all so smooth.", color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

            Button(
                modifier = Modifier.fillMaxWidth(0.46f).fillMaxHeight(0.6f),
                onClick = { },
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    bottomStart = 24.dp
                ), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF15E31))) {

                Text(text = "Let's Start",
                    color = Color.White, fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

            }

        }
    }
}

@Composable
fun onBoardImage3()
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        contentAlignment = Alignment.CenterEnd
    )
    {
        val painter = rememberAsyncImagePainter(R.drawable.onboard3)
        val state = painter.state
        Image(painter = painter, contentDescription = "logo image")
        if (state is AsyncImagePainter.State.Loading)
        {
            CircularProgressIndicator()
        }
    }
}
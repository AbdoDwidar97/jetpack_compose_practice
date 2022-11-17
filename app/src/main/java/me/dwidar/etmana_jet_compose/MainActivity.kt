package me.dwidar.etmana_jet_compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import me.dwidar.etmana_jet_compose.onBoardingPages.view.OnBoardActivity
import me.dwidar.etmana_jet_compose.ui.theme.Etmana_jet_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            delay(3000)

            val intent = Intent(this@MainActivity, OnBoardActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContent {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 30.dp),
            ) {
                Spacer(Modifier.fillMaxHeight(0.16f))
                // PaddingValues(horizontal = 10.dp, )
                Text(text = "Wish it,", color = Color(0xFFED278D), fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text(text = "Click it", color = Color(0xFFFCB116), fontSize = 26.sp, fontWeight = FontWeight.Bold)

                Spacer(Modifier.fillMaxHeight(0.3f))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    coilImage()
                }

            }
        }
    }
}


@Composable
fun coilImage()
{
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.2f),
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

/// ------------------------------------------------------

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Etmana_jet_composeTheme {
        Greeting("Android")
    }
}
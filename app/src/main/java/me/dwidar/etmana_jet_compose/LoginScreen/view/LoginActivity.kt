package me.dwidar.etmana_jet_compose.LoginScreen.view

import android.content.Context
import android.media.MediaRouter2.ControllerCallback
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import me.dwidar.etmana_jet_compose.LoginScreen.model.CountryCode
import me.dwidar.etmana_jet_compose.R
import me.dwidar.etmana_jet_compose.ui.theme.Etmana_jet_composeTheme

class LoginActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContent {
            loginScreenWidget()
        }
    }
}


@Composable
@Preview
fun loginScreenWidget()
{
    var navHostController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
    ) {

        backButton(navHostController)

        Spacer(modifier = Modifier.fillMaxHeight(0.16f))

        loginLogoImage()

        Text(text = "Login to Your Account", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.fillMaxHeight(0.01f))

        Divider(
            modifier = Modifier
                .fillMaxWidth(0.06f)
                .fillMaxHeight(0.01f)
                .background(color = Color.Green)
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.14f))

        phoneNumberWidget()

        Spacer(modifier = Modifier.fillMaxHeight(0.06f))

        passwordWidget()

        Spacer(modifier = Modifier.fillMaxHeight(0.04f))

        Row(modifier = Modifier.fillMaxWidth() ,horizontalArrangement = Arrangement.Center) {
            Text("Forget Password?", fontSize = 16.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.14f))

        Row(modifier = Modifier.fillMaxWidth() ,horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.22f), onClick = {}, shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3F51B5)
            )) {

                Text(text = "Login",
                    color = Color.White, fontSize = 16.sp,
                    textAlign = TextAlign.Center)

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun backButton(navHostController: NavHostController)
{
    CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
        IconButton(
            onClick = {
                navHostController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth(0.1f)
                .fillMaxHeight(0.04f)
                .background(color = Color(0xFFE0DFDF), shape = RoundedCornerShape(10.dp))
        ){
            Icon(Icons.Filled.ArrowBackIos, "Back",
                Modifier
                    .size(1000.dp)
                    .padding(start = 10.dp))
        }
    }

}

@Composable
fun loginLogoImage()
{
    Box(
        modifier = Modifier
            .fillMaxWidth(0.32f)
            .fillMaxHeight(0.11f),
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun phoneNumberWidget()
{
    var options = listOf(CountryCode(code = "+20", flag = R.drawable.egypt), CountryCode(code = "+966", flag = R.drawable.saudi))
    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf(options[0]) }

    var phoneNumber by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth())
    {
        Text(text = "Phone Number", fontSize = 14.sp, color = Color.Black)

        Row {

            Box(modifier =
            Modifier
                .fillMaxWidth(0.38f)
                .fillMaxHeight(0.13f)
                .background(color = Color.White)
            )
            {
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded})
                {
                    OutlinedTextField(
                        value = selectedCountry.code,
                        onValueChange = {},
                        modifier = Modifier.background(color = Color.White),
                        readOnly = true,
                        leadingIcon = {dropDownImage(selectedCountry)},
                        trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )

                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
                    {

                        options.forEach{
                                countryCode -> DropdownMenuItem( onClick = {
                            selectedCountry = countryCode
                            expanded = false
                        })
                            {

                            dropDownImage(countryCode)
                            Text(text = countryCode.code)
                        }
                        }

                    }

                }
            }

            Spacer(modifier = Modifier.fillMaxWidth(0.03f))

            OutlinedTextField(
                shape = RoundedCornerShape(14),
                value = phoneNumber,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = {pn -> phoneNumber = pn},
                modifier = Modifier.background(color = Color.White)
            )

        }
    }

}

@Composable
fun dropDownImage(countryCode: CountryCode)
{
    Box(
        modifier = Modifier
            .fillMaxWidth(0.18f)
            .fillMaxHeight(0.16f),
        contentAlignment = Alignment.Center)
    {
        val painter = rememberAsyncImagePainter(countryCode.flag)
        val state = painter.state
        Image(painter = painter, contentDescription = "Country Code")
        if (state is AsyncImagePainter.State.Loading)
        {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun passwordWidget()
{
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth())
    {
        Text(text = "Password", fontSize = 14.sp, color = Color.Black)

        OutlinedTextField(
            shape = RoundedCornerShape(14),
            value = password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {pwd -> password = pwd},
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        )
    }
}
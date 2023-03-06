package com.cyberwalker.fashionstore.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.home.HomeScreenActions
import com.cyberwalker.fashionstore.splash.SplashScreenActions

@Composable
fun LoginScreen(onAction: (actions: SplashScreenActions) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .width(80.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.splash_cta),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(30.dp))
        Login(onAction = onAction)
        GoogleLogin(onAction = onAction)
    }

}

@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
    onAction: (actions: SplashScreenActions) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val email = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        TextField(
            modifier = Modifier
                .background(Color.White),
            value = email.value,
            singleLine = false,
            onValueChange = {
                email.value = it
            },
            placeholder = {
                Text(text = "email", color = Color.LightGray)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            modifier = Modifier
                .background(Color.White),
            value = password.value,
            singleLine = false,
            onValueChange = {
                password.value = it
            },
            placeholder = {
                Text(text = "password", color = Color.LightGray)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.loginWithEmail(email.value.text, password.value.text, onAction)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        )
        {
            Text(text = "Login", color = Color.White)
        }
    }
}


@Composable
fun GoogleLogin( viewModel: LoginViewModel = hiltViewModel(),
                 onAction: (actions: SplashScreenActions) -> Unit) {
    Button(
        onClick = {
//            viewModel.loginWithGoogle()
                  },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(0.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = RoundedCornerShape(28.dp),
        contentPadding = PaddingValues(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = Color.Red
        ),
        border = BorderStroke(1.dp, Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    modifier = Modifier
                        .size(38.dp),
                    contentDescription = "drawable_icons",
                    tint = Color.Unspecified
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Google",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
//                fontFamily = FontFamily(
//                    Font(
//                        R.font.roboto_medium
//                    )
//                )
            )
        }
    }
}

@Preview
@Composable
fun GoogleLoginPrev() {
//    GoogleLogin()
}

//@Preview
//@Composable
//fun loginPreview() {
//    val viewModel: LoginViewModel = hiltViewModel()
////    Login(viewModel)
//}


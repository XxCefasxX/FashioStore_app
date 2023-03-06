package com.cyberwalker.fashionstore.data.Profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

val currentUser = FirebaseAuth.getInstance().currentUser

@Composable
fun Profile(navController: NavHostController) {

    currentUser?.let { user ->
        Column(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                logOut()
                navController.navigate(Screen.Login.route)
            }) {
                Text(text = "Logout")
            }
            Text(text = user.email.toString())
        }
    }

}

fun logOut() {
    FirebaseAuth.getInstance().signOut()
}
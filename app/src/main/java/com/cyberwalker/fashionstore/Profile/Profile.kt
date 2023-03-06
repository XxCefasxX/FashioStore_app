package com.cyberwalker.fashionstore.Profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.dump.BottomNav
import com.cyberwalker.fashionstore.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

val currentUser = FirebaseAuth.getInstance().currentUser

@Composable
fun Profile(navController: NavHostController) {

    currentUser?.let { user ->

        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            //All elements
            Column {
                Button(onClick = {
                    logOut()
                    navController.navigate(Screen.Login.route)
                }) {
                    Text(text = "Logout")
                }
                Text(text = user.email.toString())
            }

            //LAST ROW
            Row(
                modifier = Modifier
                    .weight(1f, false)
            ) {
                BottomNav(navController = navController)
            }
        }
    }

}

fun logOut() {
    FirebaseAuth.getInstance().signOut()
}
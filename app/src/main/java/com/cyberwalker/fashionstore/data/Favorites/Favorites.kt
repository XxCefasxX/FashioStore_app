package com.cyberwalker.fashionstore.data.Favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.dump.BottomNav

@Composable
fun FavoritesScreen(navController: NavHostController) {


    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        //All elements
        Column {
            Text(text = "This is the favorites screen")
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
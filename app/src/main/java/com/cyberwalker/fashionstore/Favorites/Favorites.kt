package com.cyberwalker.fashionstore.Favorites

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.data.model.Clothes
import com.cyberwalker.fashionstore.dump.BottomNav
import com.cyberwalker.fashionstore.ui.theme.cardColorBlue
import com.cyberwalker.fashionstore.ui.theme.cardColorGreen
import com.cyberwalker.fashionstore.ui.theme.cardColorYellow
import com.cyberwalker.fashionstore.ui.theme.small_caption2
import java.util.*

val bgColors = listOf<Color>(cardColorYellow, cardColorBlue, cardColorGreen, cardColorGreen)

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    favoritesVM: FavoritesViewModel = hiltViewModel()
) {

//    val _favorites = favoritesVM.getFavorites()
    val _favorites by favoritesVM.Favorites.observeAsState()
    favoritesVM.getFavorites()
    Log.d(TAG, "FavoritesScreen: $_favorites")
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            _favorites?.let {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(_favorites!!.size) { fav ->
                        FavoriteItem(favorite = _favorites!![fav])
                    }

                }
            }

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

@Composable
fun FavoriteItem(favorite: Clothes,favoritesVM: FavoritesViewModel = hiltViewModel()) {
    val favoriteBG = bgColors.random()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(color = favoriteBG)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(92.dp, 144.dp)
                    .weight(2f),
                painter = painterResource(id = favorite.picture),
                contentDescription = null
            )
            Column(Modifier.weight(3f)) {
                Text(text = favorite.name, style = MaterialTheme.typography.small_caption2)
                Text(
                    text = "₹${favorite.price.toString()}",
                    style = MaterialTheme.typography.small_caption2
                )

            }
            Image(
                painter = painterResource(id = R.drawable.ic_heart_filled),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp, top = 8.dp)
                    .weight(1f)
                    .clickable {
                        favoritesVM.addRemoveFavorite(
                            favorite.name,
                            favorite.price,
                            favorite.picture
                        )
                    }
            )
        }
    }
}
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.data.model.Clothes
import com.cyberwalker.fashionstore.dump.BottomNav
import com.cyberwalker.fashionstore.ui.theme.*
import java.util.*

val bgColors = listOf<Color>(
    cardColorYellow,
    cardColorBlue,
    cardColorGreen,
    cardColorGreen2,
    cardColorPurple,
    cardColorPink,
    cardColorOrange
)

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

            if (_favorites != null) {
                Log.d(TAG, "FavoritesScreen: cccc")
                _favorites?.let {
                    if (it.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(_favorites!!.size) { fav ->
                                FavoriteItem(favorite = _favorites!![fav])
                            }

                        }

                    } else {
                        EmptyFavorites()
                    }
                }
            } else {
                EmptyFavorites()
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
fun FavoriteItem(favorite: Clothes, favoritesVM: FavoritesViewModel = hiltViewModel()) {
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
                painter = rememberAsyncImagePainter(favorite.picture),
                contentDescription = null
            )
            Column(Modifier.weight(3f)) {
                Text(
                    text = favorite.name, style = MaterialTheme.typography.small_caption2,
                    fontSize = 20.sp
                )
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

@Composable
fun EmptyFavorites() {
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
    ) {
        Text(
            text = "Continue exploring and add some article :)", color = Color.Gray,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }

}


@Preview
@Composable
fun EmptyFavoritesPrev() {
    EmptyFavorites()
}
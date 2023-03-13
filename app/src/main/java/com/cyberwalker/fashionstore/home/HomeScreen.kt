/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cyberwalker.fashionstore.home

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.cyberwalker.fashionstore.Favorites.FavoritesViewModel
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.data.model.Clothes
import com.cyberwalker.fashionstore.detail.DetailViewModel
import com.cyberwalker.fashionstore.dump.BottomNav
import com.cyberwalker.fashionstore.dump.vertical
import com.cyberwalker.fashionstore.ui.theme.*

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onAction: (actions: HomeScreenActions) -> Unit,
    navController: NavHostController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNav(navController = navController)
        }
    ) { innerPadding ->
        HomeScreenContent(modifier = Modifier.padding(innerPadding), onAction = onAction)
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier,
    onAction: (actions: HomeScreenActions) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Home Screen" }
    ) {
        Row(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(width = 37.dp, height = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Spacer(
                    modifier = Modifier
                        .size(width = 37.dp, height = 40.dp)
                        .background(color = ltgray, shape = RoundedCornerShape(12.dp))
                )
                Image(
                    modifier = Modifier
                        .size(width = 32.dp, height = 32.dp),
                    painter = painterResource(id = R.drawable.ic_man),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(24.dp))
            Column {
                Text(text = "Welcome", style = MaterialTheme.typography.small_caption)
                Text(text = "Hi Babloo", style = MaterialTheme.typography.medium_14)
            }
            Spacer(modifier = Modifier.weight(1F))
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { }
                    .padding(12.dp),
                painter = painterResource(id = R.drawable.menu_bar),
                contentDescription = null
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 40.dp, end = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Today's Promo", style = MaterialTheme.typography.medium_18)
            Spacer(modifier = Modifier.size(40.dp))
            Image(modifier = Modifier
                .size(50.dp)
                .clickable { }
                .padding(16.dp),
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null)
        }

        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .defaultMinSize(minHeight = 228.dp)
        ) {
            TabRow()
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                modifier = Modifier.defaultMinSize(300.dp, 264.dp),
                painter = painterResource(id = R.drawable.img_preview),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .padding(start = 40.dp, end = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "New Arrivals", style = MaterialTheme.typography.medium_18)
            Spacer(modifier = Modifier.size(40.dp))
            Image(modifier = Modifier
                .size(50.dp)
                .clickable { }
                .padding(16.dp),
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null)
        }

        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .defaultMinSize(minHeight = 228.dp)
        ) {
            TabRow()
            Spacer(modifier = Modifier.size(16.dp))
            GridOfImages(onAction = onAction)
        }
    }
}

@Composable
private fun TabRow() {
    Row(
        modifier = Modifier
            .vertical()
            .rotate(-90F), verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(4.dp)
                .background(color = ltgray_dot, shape = CircleShape)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Spacer(
            modifier = Modifier
                .size(4.dp)
                .background(color = ltgray_dot, shape = CircleShape)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Spacer(
            modifier = Modifier
                .size(4.dp)
                .background(color = ltgray_dot, shape = CircleShape)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = "Belt", style = MaterialTheme.typography.medium_18)
        Spacer(modifier = Modifier.size(24.dp))
        Box(
            modifier = Modifier
                .size(70.dp, 28.dp)
                .background(color = highlight, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Cloths", style = MaterialTheme.typography.medium_18, color = Color.White)
        }
        Spacer(modifier = Modifier.size(32.dp))
        Text(text = "Shoes", style = MaterialTheme.typography.medium_18)
    }
}


@Composable
fun GridOfImageItemLeft(
    name: String,
    price: Float,
    img: String,
    favorite: Int,
    onAction: (actions: HomeScreenActions) -> Unit,
    favoritesVM: FavoritesViewModel = hiltViewModel(),
    detailsVM: DetailViewModel = hiltViewModel()
) {
    val bgColors = listOf<Color>(cardColorYellow, cardColorBlue, cardColorGreen, cardColorGreen)
    val cardBG = bgColors.random()
    Box(
        Modifier
            .size(120.dp, 150.dp)
            .background(color = cardBG, shape = RoundedCornerShape(16.dp))
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable {
                detailsVM.getDetails(name)
                onAction(HomeScreenActions.Details)
            }
    ) {
        Image(
            modifier = Modifier
                .size(92.dp, 144.dp)
                .align(Alignment.BottomCenter),
            painter = rememberAsyncImagePainter(img),
//            painter = rememberAsyncImagePainter("https://www.pngfind.com/pngs/m/52-524008_women-fashion-model-png-transparent-png.png"),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = favorite),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp, top = 8.dp)
                .clickable {
                    favoritesVM.addRemoveFavorite(
                        name,
                        price,
                        img
                    )
                }
        )
    }
    Spacer(modifier = Modifier.size(8.dp))
    Row {
        Text(text = name, style = MaterialTheme.typography.small_caption2)
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = "₹$price", style = MaterialTheme.typography.small_caption2)
    }
}

@Composable
fun GridOfImagesItemRight(
    name: String,
    price: Float,
    img: String,
    favorite: Int,
    onAction: (actions: HomeScreenActions) -> Unit,
    favoritesVM: FavoritesViewModel = hiltViewModel(),
    detailsVM: DetailViewModel = hiltViewModel()
) {
    val itemDetails = remember(detailsVM) { detailsVM.Clothe}
    val details by itemDetails.observeAsState()
    Box(
        Modifier
            .size(120.dp, 180.dp)
            .background(color = cardColorBlue, shape = RoundedCornerShape(16.dp))
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable {

                detailsVM.getDetails(name)
                onAction(HomeScreenActions.Details)
            }
    ) {
        Image(
            modifier = Modifier
                .size(112.dp, 170.dp)
                .align(Alignment.BottomCenter),
            painter = rememberAsyncImagePainter(img),
            contentDescription = null
        )

        Image(
            painter = painterResource(id = favorite),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp, top = 8.dp)
                .clickable {
                    favoritesVM.addRemoveFavorite(
                        name,
                        price,
                        img
                    )
//                            Log.d(TAG, "GridOfImages: $_favorites")
                }
        )
    }
    Spacer(modifier = Modifier.size(8.dp))
    Row {
        Text(text = name, style = MaterialTheme.typography.small_caption2)
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "₹$price", style = MaterialTheme.typography.small_caption2)
    }
}

@Composable
private fun GridOfImages(
    onAction: (actions: HomeScreenActions) -> Unit,
    favoritesVM: FavoritesViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val _storeClothes by homeViewModel.Clothes.observeAsState()
    val _favorites by favoritesVM.Favorites.observeAsState()
    favoritesVM.getFavorites()
    homeViewModel.getClothes()



    Log.d(TAG, "GridOfImages: clothes $_storeClothes")
    Row(modifier = Modifier.fillMaxWidth()) {
        _storeClothes?.let {
            val storeSize = _storeClothes?.size ?: 0

            var middleIndex = storeSize / 2
            if (storeSize % 2 > 0) {
                middleIndex += 1
            }

            Log.d(TAG, "GridOfImages: mid $middleIndex")
            val firstHalf = _storeClothes?.subList(0, middleIndex)
            val secondHalf = _storeClothes?.subList(middleIndex, storeSize)
            Log.d(TAG, "GridOfImages: first = $firstHalf")
            Log.d(TAG, "GridOfImages: second = $secondHalf")
            Column {

                for (article in 0 until firstHalf!!.size) {
                    GridOfImageItemLeft(
                        name = firstHalf[article].name,
                        price = firstHalf[article].price,
                        img = firstHalf[article].picture,
                        favorite = isFavorite(firstHalf[article].name, _favorites),
                        onAction = onAction
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                }


            }
            Spacer(modifier = Modifier.size(24.dp))
            Column {
                for (article in 0 until secondHalf!!.size) {
                    GridOfImagesItemRight(
                        name = secondHalf[article].name,
                        price = secondHalf[article].price,
                        img = secondHalf[article].picture,
                        favorite = isFavorite(secondHalf[article].name, _favorites),
                        onAction = onAction
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                }

            }
        }
    }
}

fun isFavorite(name: String, _favorites: List<Clothes>?): Int {
    var heartImg = R.drawable.ic_heart
    _favorites?.let {
        for (favorite in _favorites) {
            if (favorite.name == name)
                heartImg = R.drawable.ic_heart_filled
        }
    }
    return heartImg
}

sealed class HomeScreenActions {
    object Details : HomeScreenActions()
}
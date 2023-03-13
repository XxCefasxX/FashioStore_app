package com.cyberwalker.fashionstore.data.model

import android.graphics.drawable.Drawable
import com.cyberwalker.fashionstore.R

data class Clothes(
    val name: String = "",
    val price: Float = 0f,
    val picture: String = "https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png",
    val description: String = "",
    val isUserFavorite: Int = R.drawable.ic_heart_filled,
    val sizes: ArrayList<String> = ArrayList<String>()
)
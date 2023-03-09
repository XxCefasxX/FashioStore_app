package com.cyberwalker.fashionstore.Favorites

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.*
import com.cyberwalker.fashionstore.data.model.Clothes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "FavoritesViewModel"

class FavoritesViewModel : ViewModel() {
    val userDBRegister = FirebaseFirestore.getInstance()
    val currentUser = FirebaseAuth.getInstance().currentUser

    private val _Favorites = MutableLiveData<List<Clothes>>()
    val Favorites: LiveData<List<Clothes>> = _Favorites



    fun addRemoveFavorite(name: String, price: Float, img: String) {
        val favorite = Clothes(name, price, img)
        var favs = Favorites.value
        Log.d(TAG, "addRemoveFavorite: favs= $favs")

        currentUser?.uid?.let { userID ->
            userDBRegister.collection("Users")
                .document(userID!!)
                .collection("Favorites").get().addOnSuccessListener { userFavorites ->
                    var isNew = true
                    if (userFavorites.size() > 0) {

                        for (fav in userFavorites) {
                            if (fav["name"] == name) {
                                isNew = false

                                userDBRegister.collection("Users")
                                    .document(userID)
                                    .collection("Favorites")
                                    .document(name)
                                    .delete()
                                    .addOnSuccessListener {
                                        Log.d(TAG, "addRemoveFavorite: deleted")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.d(TAG, "addRemoveFavorite: fail deleted")
                                    }
                                break
                            }
                        }
                    }
                    if (isNew) {
                        userDBRegister.collection("Users")
                            .document(userID!!)
                            .collection("Favorites")
                            .document(name)
                            .set(
                                hashMapOf(
                                    "name" to name,
                                    "price" to price,
                                    "img" to img
                                )
                            )
                    }
                    getFavorites()
                }
        }


//        val cf = ArrayList<Clothes>()
//        favs?.let {
//            for (clothe in favs!!) {
//                Log.d(TAG, "addRemoveFavorite: adding previus $clothe")
//                cf.add(clothe)
//            }
//        }
//
//        cf.add(favorite)
//        favs = cf
//        Log.d(TAG, "addRemoveFavorite: favs 2= $favs")
//
//        viewModelScope.launch {
//            _f.postValue(favorite)
//            Log.d(TAG, "addRemoveFavorite: f=${_f.value}")
//            Log.d(TAG, "addRemoveFavorite: adding to favorites $favs")
////            _Favorites.value = favs
//            _Favorites.postValue(favs)
//            Log.d(TAG, "addRemoveFavorite: added to favorites ${Favorites.value}")
//            Log.d(TAG, "addRemoveFavorite: added to _favorites ${_Favorites.value}")
//        }
    }


    fun getFavorites() {
        var Favs = ArrayList<Clothes>()
        currentUser?.uid?.let { userID ->
            userDBRegister.collection("Users")
                .document(userID!!)
                .collection("Favorites").get().addOnSuccessListener { userFavorites ->
                    for (fav in userFavorites) {
                        Favs.add(
                            Clothes(
                                fav["name"].toString(),
                                fav["price"].toString().toFloat(),
                                fav["img"].toString()
                            )
                        )
                    }
                    _Favorites.value = Favs
                    _Favorites.postValue(Favs)
                }
        }

    }
}
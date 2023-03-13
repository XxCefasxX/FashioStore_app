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
package com.cyberwalker.fashionstore.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.cyberwalker.fashionstore.Favorites.FavoritesViewModel
import com.cyberwalker.fashionstore.R
import com.cyberwalker.fashionstore.data.model.Clothes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DetailViewModel"

val storeDB = FirebaseFirestore.getInstance()

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    private val _Clothe = MutableLiveData<Clothes>()
    val Clothe: LiveData<Clothes> = _Clothe


    fun getDetails(name: String) {
        storeDB.collection("Store")
            .document("Articles")
            .collection("Cloths")
            .document(name).get().addOnSuccessListener { item ->
                val selectedClothe = Clothes(
                    name = item["name"].toString(),
                    price = item["price"].toString().toFloat(),
                    picture = item["img"].toString(),
                    description = item["description"].toString(),
                    sizes = item["sizes"] as ArrayList<String>
                )
                _Clothe.postValue(selectedClothe)
                Log.d(TAG, "getDetails: details=${Clothe.value}")
            }
    }

}

data class DetailUiState(
    val txt: String? = null
)
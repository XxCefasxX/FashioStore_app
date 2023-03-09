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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.cyberwalker.fashionstore.data.model.Clothes
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

val userDBRegister = FirebaseFirestore.getInstance()

@HiltViewModel
class HomeViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    private val _Clothes = MutableLiveData<List<Clothes>>()
    val Clothes: LiveData<List<Clothes>> = _Clothes

    fun getClothes() {
        var clothes = ArrayList<Clothes>()
        userDBRegister.collection("Store")
            .document("Articles")
            .collection("Cloths").get().addOnSuccessListener { sttoreClothes ->
                for (item in sttoreClothes) {
                    val name = item["name"].toString() ?: ""
                    val price = item["price"].toString().toFloat() ?: 0f
                    val img = item["img"].toString() ?: ""
                    if (name != "") {
                        clothes.add(
                            Clothes(
                                name,
                                price,
                                img
                            )
                        )
                    }

                }
                _Clothes.postValue(clothes)
            }
    }

}

data class HomeUiState(
    val loadComplete: Boolean = false
)
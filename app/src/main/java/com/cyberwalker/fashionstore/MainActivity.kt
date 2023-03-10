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
package com.cyberwalker.fashionstore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cyberwalker.fashionstore.navigation.FashionNavGraph
import com.cyberwalker.fashionstore.ui.theme.FashionStoreTheme
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(this) {
            if (!it.isSuccessful) {
                //Could not get FirebaseMessagingToken
                Log.d(TAG, "No Token")
            }
            if (null != it.result) {
                //Got FirebaseMessagingToken
                val firebaseMessagingToken: String = it.result
                Log.d(TAG, "Token: $firebaseMessagingToken")
                //Use firebaseMessagingToken further
            }
        }

        val intent = this.intent

        val extras = intent.extras
        if (extras != null) {
            val body = intent.getStringExtra("body")
            Log.d(TAG, "onCreate: body= $body")
        } else {
            Log.w("myTag", "extras is null")
        }
//        val ss:String = intent.getStringExtra("body").toString()
//        val intent_o = intent
//        Log.d(TAG, "onCreate: $intent_o")
//        Log.d(TAG, "onCreate: body= $ss")
//        if (intent_o != null) {
//            val body = intent_o.getStringExtra("body")
//            Log.d(TAG, "onCreate: body= $body")
//        }
        setContent {
            FashionStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    FashionNavGraph()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FashionStoreTheme {
        App()
    }
}
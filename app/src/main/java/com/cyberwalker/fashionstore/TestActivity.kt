package com.cyberwalker.fashionstore

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "TestActivity"
@AndroidEntryPoint
class TestActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val intent = this.intent

        val extras = intent.extras
        if (extras != null) {
            val body = intent.getStringExtra("body")
            Log.d(TAG, "onCreate: body= $body")
        } else {
            Log.w("myTag", "extras is null")
        }
    }
}
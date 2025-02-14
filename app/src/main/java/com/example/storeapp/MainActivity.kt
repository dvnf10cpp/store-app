package com.example.storeapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.storeapp.ui.routes.Navigation
import com.example.storeapp.ui.theme.StoreAppTheme
import com.example.storeapp.utils.Event
import com.example.storeapp.utils.EventBus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StoreAppTheme {
                val lifecycle = LocalLifecycleOwner.current.lifecycle
                
                LaunchedEffect(key1 = lifecycle) {
                    repeatOnLifecycle(
                        state = Lifecycle.State.STARTED
                    ) {
                        EventBus.events.collect { event ->
                            if (event is Event.Toast) {
                                Toast.makeText(
                                    this@MainActivity,
                                    event.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
                Navigation()
            }
        }
    }
}

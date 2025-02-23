package com.mangabible

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.mangabible.ui.screen.SplashScreen
import com.mangabible.ui.viewmodel.splash.SplashViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: SplashViewModel = koinViewModel()
                    SplashScreen()
                    LaunchedEffect(key1 = true) {
                        delay(2000)
                        val intent = if (viewModel.isLoggedIn()) {
                            Intent(this@SplashActivity, MainActivity::class.java)
                        } else {
                            Intent(this@SplashActivity, LoginActivity::class.java)
                        }
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}

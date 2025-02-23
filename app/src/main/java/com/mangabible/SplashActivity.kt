package com.mangabible

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.mangabible.ui.screen.SplashScreen
import com.mangabible.ui.theme.AnimeBibleTheme
import com.mangabible.ui.viewmodel.splash.SplashViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeBibleTheme {
                val viewModel: SplashViewModel = koinViewModel()
                SplashScreen()
                LaunchedEffect(key1 = true) {
                    delay(2000)
                    val intent = Intent(
                        this@SplashActivity,
                        MainActivity::class.java.takeIf { viewModel.isLoggedIn() }
                            ?: LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}

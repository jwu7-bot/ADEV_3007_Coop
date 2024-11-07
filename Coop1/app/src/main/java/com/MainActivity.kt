package com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lottieanimation.AnimationScreen1
import com.example.lottieanimation.AnimationScreen2
import com.example.lottieanimation.AnimationScreen3
import com.example.lottieanimation.Loading
import com.example.lottieanimation.ui.theme.LottieAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottieAnimationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController as NavHostController,
                        startDestination = "animation1"
                    ) {
                        composable("animation1") {
                            AnimationScreen1(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("animation2") {
                            AnimationScreen2(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("animation3") {
                            AnimationScreen3(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("loading") {
                            Loading(modifier = Modifier.padding(innerPadding), navController)
                        }
                    }
                }
            }
        }
    }
}

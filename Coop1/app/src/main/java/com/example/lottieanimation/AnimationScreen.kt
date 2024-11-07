package com.example.lottieanimation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// LOTTIE Imports
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AnimationScreen1(
    modifier: Modifier,
    navController: NavHostController
) {
    // loads the animation file from resources.
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.food))

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Lottie Compose
        LottieAnimation(
            modifier = Modifier.size(400.dp),
            composition = composition,
            iterations = 1, //LottieConstants.IterateForever
            //speed = 2f
        )

        // Title
        Text(
            text = "Fresh Food",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        // Subtitle
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Next button
        Button(
            onClick = { navController.navigate("animation2") },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Next")
        }
    }
}
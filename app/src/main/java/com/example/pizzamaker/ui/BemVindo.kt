package com.example.pizzamaker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pizzamaker.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.pizza),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black) // This is an example of a purple color
                    .padding(8.dp) // Padding around the text inside the box
            ) {
                Text(
                    text = "Bem Vindo a FoodPlanet! App",
                    color = Color.White, // Setting text color to white for contrast
                    modifier = Modifier.padding(4.dp) // Optional: additional padding for the text
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(NavRoutes.PizzaBuilderScreen) }) {
                Text("Comece a fazer sua pizza!")
            }
        }
    }
}